package com.app.petitionatlas.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.legacy.app.ActionBarDrawerToggle
import com.app.petitionatlas.IconTextView
import com.app.petitionatlas.R
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.dashboard.model.DashboardInteractorImpl
import com.app.petitionatlas.mvp.dashboard.presentor.DashboardPresentor
import com.app.petitionatlas.mvp.dashboard.presentor.DashboardPresentorImpl
import com.app.petitionatlas.mvp.dashboard.view.DashboardView
import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractorImpl
import com.app.petitionatlas.mvp.login_mvp.presentor.LoginPresentorImpl
import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.sharedvalues.SharedValues
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.slidinglayout.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.IOException
import java.util.*

class HomeActivity : AppCompatActivity(), View.OnClickListener, DashboardView {
    var mDrawerToggle: ActionBarDrawerToggle? = null
    lateinit var sharedValues: SharedValues
    lateinit var context: Activity
    lateinit var dashboardPresentor: DashboardPresentor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        context = this@HomeActivity

        sharedValues = SharedValues(context)

        if (GlobalVariabels.isNetworkConnected(context)) {
            setPresentor()
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

        if (sharedValues.getpreferance(SharedValues.USERNAME)?.trim()?.length!! > 0) {
            tv_dashboardiconuserName?.setText(sharedValues.getpreferance(SharedValues.USERNAME))
        }
        if (sharedValues.getpreferance(SharedValues.MAINCAMPAIGNNAME)?.trim()?.length!! > 0) {
            tv_compaign?.setText(sharedValues.getpreferance(SharedValues.MAINCAMPAIGNNAME))
        }
        if (sharedValues.getpreferance(SharedValues.IsAdmin)?.trim()?.length!! > 0) {
            if (sharedValues.getpreferance(SharedValues.IsAdmin).equals("true")) {
                tv_dashboardadmin.visibility = View.VISIBLE
            }
        }
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.drawable.ic_menu_black_24dp,
            R.string.drawer_opened,
            R.string.drawer_closed
        ) {
            override fun onDrawerClosed(view: View) {
                ActivityCompat.invalidateOptionsMenu(this@HomeActivity)
            }

            override fun onDrawerOpened(drawerView: View) {
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(drawer_layout.getApplicationWindowToken(), 0)
                ActivityCompat.invalidateOptionsMenu(this@HomeActivity)
            }
        }
        drawer_layout.setDrawerListener(mDrawerToggle)
        drawer_layout.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS)
        invalidateOptionsMenu()
        iv_menu_image.setOnClickListener(this)
        cl_dashboard.setOnClickListener(this)
        cl_management.setOnClickListener(this)
        cl_logoutperson?.setOnClickListener(this)
        cl_addcontact?.setOnClickListener(this)
        cl_searchvoters?.setOnClickListener(this)
    }

    private fun setPresentor() {
        dashboardPresentor = DashboardPresentorImpl()
        dashboardPresentor.attachview(this, DashboardInteractorImpl())
        Log.d("DATALOSS", "whrgrwhgw " + sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID))
        if (SharedValues.MAINCAMPAIGNID != null && SharedValues.MAINCAMPAIGNID?.trim()?.length > 0) {
            dashboardPresentor.dashboardid(sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID))
        }
    }

    override fun onResume() {
        super.onResume()
        if (GlobalVariabels.isNetworkConnected(context)) {
            setPresentor()
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.iv_menu_image -> drawer_layout!!.openDrawer(rl_left_drawer!!)
            R.id.cl_dashboard -> drawer_layout!!.closeDrawers()
            R.id.cl_management -> {
                drawer_layout!!.closeDrawers()
                val intent = Intent(this@HomeActivity, WalksheetActivity::class.java)
                startActivity(intent)
            }
            R.id.cl_logoutperson -> {
                drawer_layout!!.closeDrawers()
                sharedValues.removealldata()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            R.id.cl_addcontact -> {
                drawer_layout!!.closeDrawers()
                val intent = Intent(this@HomeActivity, AddContactActivity::class.java)
                startActivity(intent)
            }
            R.id.cl_searchvoters -> {
                drawer_layout!!.closeDrawers()
                val intent = Intent(this@HomeActivity, SearchVoter::class.java)
                startActivity(intent)
            }
        }
    }

    override fun OnSuccess(dashboardResponse: DashboardResponse?) {
        Toast.makeText(context, "Succcess", Toast.LENGTH_SHORT)
        if (dashboardResponse != null) {
            if (dashboardResponse.getVoterCount() != null && dashboardResponse.getVoterCount()
                    ?.trim()!!.length > 0
            ) {
                tv_answer1.text = dashboardResponse.getVoterCount();
            }
            if (dashboardResponse.getTargetedSignaturesCount() != null && dashboardResponse.getTargetedSignaturesCount()
                    ?.trim()!!.length > 0
            ) {

                tv_answer2.text = dashboardResponse.getTargetedSignaturesCount();
            }
            if (dashboardResponse.getCollectedSignaturesCount() != null && dashboardResponse.getCollectedSignaturesCount()
                    ?.trim()!!.length > 0
            ) {
                tv_answer3.text = dashboardResponse.getCollectedSignaturesCount();
            }
            var collectedsignatute: Int = 0
            var multiple: Int = 0
            var totalpercentage: Int = 0

            if (dashboardResponse.getCollectedSignaturesCount() != null && dashboardResponse.getCollectedSignaturesCount()
                    ?.trim()!!.length > 0
            ) {
                if (dashboardResponse.getTargetedSignaturesCount() != null && dashboardResponse.getTargetedSignaturesCount()
                        ?.trim()!!.length > 0
                ) {

                    collectedsignatute = dashboardResponse.getCollectedSignaturesCount()!!.toInt()
                    if (collectedsignatute != 0) {
                        multiple = collectedsignatute!! * 100
                        var targetSignature: Int =
                            dashboardResponse.getTargetedSignaturesCount()!!.toInt()
//                        totalpercentage = multiple / 1000
                        if (targetSignature != 0) {
                            totalpercentage = multiple / targetSignature
                        }

                    }
                    if (totalpercentage != 0) {
                        pb_percentagebar.progress = totalpercentage.toInt()
                        tv_percentage.text = totalpercentage.toString() + " %"

                        var xPosition =
                            (((pb_percentagebar.right - pb_percentagebar.left) / pb_percentagebar.max) * totalpercentage.toInt())/* + pb_percentagebar.left*/
//                        tv_percentage.translationX = xPosition.toFloat()/* - (tv_percentage.width)*/
                    } else {
                        tv_percentage.text = "0 %"
                    }
                }
            }
        }
    }

    override fun OnFailure(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Loading"
        )
    }

    override fun hideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }
}