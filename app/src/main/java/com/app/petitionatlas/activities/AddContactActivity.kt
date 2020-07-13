package com.app.petitionatlas.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Toast
import com.app.petitionatlas.R
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.addcontact_mvp.model.AddContactInteractorImpl
import com.app.petitionatlas.mvp.addcontact_mvp.presentor.AddContactPresentor
import com.app.petitionatlas.mvp.addcontact_mvp.presentor.AddContactPresentorImpl
import com.app.petitionatlas.mvp.addcontact_mvp.view.AddContactView
import com.app.petitionatlas.params.AddContactParams
import com.app.petitionatlas.params.votersupportparams.SignatureSupport
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
import com.app.petitionatlas.sharedvalues.SharedValues
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_add_contact.et_email
import kotlinx.android.synthetic.main.activity_add_contact.tv_clear
import kotlinx.android.synthetic.main.layout_supportvoter.*

class AddContactActivity : AppCompatActivity(), View.OnClickListener,AddContactView {

    lateinit var addContactPresentor:AddContactPresentor
    lateinit var sharedValues: SharedValues
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        iv_contactbackpress.setOnClickListener(this)
        tv_save.setOnClickListener(this)
        tv_clear.setOnClickListener(this)
        context = this@AddContactActivity
        sharedValues = SharedValues(context)
        setPresentor()

    }

    private fun setPresentor() {
        addContactPresentor = AddContactPresentorImpl()
        addContactPresentor.attachview(this, AddContactInteractorImpl())
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_save -> {
                if (et_email.text.toString().trim() != null && et_email.text.toString().trim().length > 0) {
                    if (Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString().trim()).matches()) {
                        if (sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID) != null && sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)?.trim()?.length!! > 0) {
                            var maincampaignid = sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)
                            mobilenumbervalidation(maincampaignid)
                        }
                    } else {
                        et_email.requestFocus()
                        et_email.setError("Please enter valid email address")
                    }
                } else {
                    if (sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID) != null && sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)?.trim()?.length!! > 0) {
                        var maincampaignid = sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)
                        mobilenumbervalidation(maincampaignid)
                    }
                }

            }
            R.id.tv_clear -> {
                et_firstname.setText(null)
                et_lastname.setText(null)
                et_housenumber.setText(null)
                et_streetname.setText(null)
                et_phone.setText(null)
                et_email.setText(null)
                et_notesforcampaign.setText(null)
            }
            R.id.iv_contactbackpress -> {
                finish()
            }
        }
    }

    private fun mobilenumbervalidation(maincampaignid: String?) {
        if (et_phone.text.toString().trim() != null && et_phone.text.toString().trim().length > 0) {
            if (et_phone.text.toString().trim().length == 10) {
                addContactPresentor.addcontact(
                    maincampaignid, getparams(
                        et_firstname.text.toString().trim(),
                        et_lastname.text.toString().trim(),
                        et_housenumber.text.toString().trim(),
                        et_streetname.text.toString().trim(),
                        et_phone.text.toString().trim(),
                        et_email.text.toString().trim(),
                        et_notesforcampaign.text.toString().trim()
                    )
                )
            } else {
                et_phone.requestFocus()
                et_phone.setError("Please enter 10 digit number")
            }
        } else {
            addContactPresentor.addcontact(
                maincampaignid, getparams(
                    et_firstname.text.toString().trim(),
                    et_lastname.text.toString().trim(),
                    et_housenumber.text.toString().trim(),
                    et_streetname.text.toString().trim(),
                    et_phone.text.toString().trim(),
                    et_email.text.toString().trim(),
                    et_notesforcampaign.text.toString().trim()
                )
            )
        }
    }

    private fun getparams(
        firstname: String,
        lastname: String,
        streetnumber: String,
        streetname: String,
        phone: String,
        email: String,
        notes: String
    ): AddContactParams {
        var addContactParams = AddContactParams()
        addContactParams.FirstName = firstname
        addContactParams.LastName =lastname
        addContactParams.StreetNumber =streetnumber
        addContactParams.StreetNameAndUnit =streetname
        addContactParams.Phone = phone
        addContactParams.Email = email
        addContactParams.Notes = notes
        return addContactParams
    }


    override fun onAddContactSuccess(string: String?) {
        Toast.makeText(context,string,Toast.LENGTH_LONG).show()
        et_firstname.text=null
        et_lastname.text=null
        et_housenumber.text=null
        et_streetname.text=null
        et_phone.text=null
        et_email.text=null
        et_notesforcampaign.text=null
    }

    override fun onAddContactFailure(error: String?) {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show()
    }

    override fun onAddContactshowprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            this@AddContactActivity,
            "Save Contact"
        )
    }

    override fun onAddContacthideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }
}