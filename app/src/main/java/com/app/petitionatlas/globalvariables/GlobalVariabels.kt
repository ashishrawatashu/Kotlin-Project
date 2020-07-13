package com.app.petitionatlas.globalvariables

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager

class GlobalVariabels {
    companion object {

        val KEY = "key"
        val COUNTY = "Select County"
        val CITY = "Select City"
        val WARD = "Select Ward"
        val PCT = "Select PCT"
        val STARTBLOCK = "Select Start Block"
        val ENDBLOCK = "Select End Block"
        val STREET = "Select Street"
        val STREETPARAMS = "Streetparams"
        val RESPONSELIST = "responselist"
        val NH_CODE = "1"
        val IN_CODE = "2"

        val FULLNAME = "fullname"
        val FULLADDRESS = "fulladdress"
        val SIGNATURE_STATUS = "signatureStatus"
        val VOTER_SUPPORT_LEVEL = "voterSupportLevel"
        val NOTES = "notes"
        val EMAIL = "email"
        val PHONE = "phone"
        val VOTER_ID = "voterid"
        val SIGNATURE_IMAGE = "signatureimage"
        val ADAPTER_POSITION = "adapterposition"



        val CONTENT_TYPE = "application/json"


        var mProgressDialog: ProgressDialog? = null

        fun showProgress(activity: Activity?, message: String?) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setMessage(message)
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.show()
        }

        fun dismissProgress() {
            try {
                if (mProgressDialog != null) {
                    if (mProgressDialog!!.isShowing) {
                        mProgressDialog!!.dismiss()
                        mProgressDialog = null
                    }
                }
            } catch (e: Exception) {
            }
        }

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun isNetworkConnected(context: Activity): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

}