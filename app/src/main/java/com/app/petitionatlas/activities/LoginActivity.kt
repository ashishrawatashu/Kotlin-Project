package com.app.petitionatlas.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.petitionatlas.R
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractorImpl
import com.app.petitionatlas.mvp.login_mvp.presentor.LoginPresentor
import com.app.petitionatlas.mvp.login_mvp.presentor.LoginPresentorImpl
import com.app.petitionatlas.mvp.login_mvp.view.LoginView
import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.sharedvalues.SharedValues
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {

    lateinit var activity: Activity
    lateinit var loginPresentor: LoginPresentor
    lateinit var sharedValues: SharedValues


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_logincardview)
        activity = this@LoginActivity
        sharedValues = SharedValues(activity)

        if (GlobalVariabels.isNetworkConnected(activity)) {
            setpresentors()
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

        btn_login.setOnClickListener(this)
    }

    private fun setpresentors() {
        loginPresentor = LoginPresentorImpl()
        loginPresentor.attachview(this, LoginInteractorImpl())
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_login -> {
                GlobalVariabels.hideKeyboard(activity)
                internetcheck()
            }
        }
    }


    private fun getlogin() {
        if (et_email != null && et_password != null) {
            if (et_email!!.text.toString().trim { it <= ' ' }.length > 0) {
                if (et_password.getText().toString().trim({ it <= ' ' }).length > 0) {
//                    val intent = Intent(activity, HomeActivity::class.java)
//                    intent.flags =
//                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
                    loginPresentor.login(
                        getparams(
                            et_email!!.text.toString(),
                            et_password.getText().toString()
                        )
                    )
                } else {
                    et_password.requestFocus()
                    et_password.setError("Password Required")
                }
            } else {
                et_email!!.requestFocus()
                et_email!!.error = "UserName Required"
            }
        }
    }

    private fun getparams(toString: String, toString1: String): LoginParams? {
        val loginParams = LoginParams()
        loginParams.name = toString
        loginParams.password = toString1
        return loginParams
    }

    override fun OnSuccess(loginResponse: LoginResponse?) {
        Toast.makeText(activity,"Login Successfully",Toast.LENGTH_SHORT)
        if (loginResponse != null) {
            savetoshared(loginResponse)
            Log.d("DATALOG","check1=> "+loginResponse.mainCampaignId)
        }
    }

    private fun savetoshared(loginResponse: LoginResponse) {
        if (loginResponse.id != null && loginResponse.id?.trim()?.length!! > 0) {
            sharedValues.AddPreference(SharedValues.USERID, loginResponse.id)
        }
        if (loginResponse.isAdmin != null && loginResponse.isAdmin?.trim()?.length!! > 0) {
            sharedValues.AddPreference(SharedValues.IsAdmin, loginResponse.isAdmin)
        }
        if (loginResponse.username != null && loginResponse.username?.trim()?.length!! > 0) {
            sharedValues.AddPreference(SharedValues.USERNAME, loginResponse.username)
        }
        if (loginResponse.mainCampaignId != null && loginResponse.mainCampaignId?.trim()?.length!! > 0) {
            sharedValues.AddPreference(
                SharedValues.MAINCAMPAIGNID,
                loginResponse.mainCampaignId
            )
        }
        if (loginResponse.mainCampaignName != null && loginResponse.mainCampaignName?.trim()?.length!! > 0) {
            sharedValues.AddPreference(
                SharedValues.MAINCAMPAIGNNAME,
                loginResponse.mainCampaignName
            )
        }
        Log.d("DATALOGS", "check" +  sharedValues.getpreferance (SharedValues.USERID))

        val intent = Intent(activity, HomeActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun OnFailure(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    override fun showprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            activity,
            "Authenticating"
        )
    }

    override fun hideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }

    fun internetcheck() {
        val check: Boolean = GlobalVariabels.isNetworkConnected(activity)
        if (check == true) {
            getlogin()
        } else {
            snackbar()
        }
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }


    fun snackbar() { //  Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No internet connection", Snackbar.LENGTH_LONG);
        val snackbar: Snackbar = Snackbar.make(
            findViewById<View>(R.id.content),
            "No internet connection",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.show()
        snackbar.setAction("RETRY", View.OnClickListener { internetcheck() })
    }


}
