package com.app.petitionatlas.sharedvalues

import android.content.Context
import android.content.SharedPreferences

class SharedValues(var con: Context) {


    companion object {
        val PREFS_NAME = "PetitionAtlas"

        //LoginDetails//
        val USERID = "id"
        val IsAdmin = "is_admin"
        val USERNAME = "username"
        val MAINCAMPAIGNID = "mainCampaignId"
        val MAINCAMPAIGNNAME = "mainCampaignName"
        val USER_LAT = "user_lat"
        val USER_LNG = "user_lng"
        val SHOWLOCATIONNAME = "show_location_name"

        var sharedpreferences: SharedPreferences? = null
    }


    init {
        sharedpreferences = con.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


    fun AddPreference(Key: String?, value: String?) {
        val editor = sharedpreferences!!.edit()
        editor.putString(Key, value)
        editor.commit()
    }

    fun getpreferance(key: String?): String? {
        return sharedpreferences!!.getString(key, "")
    }

    fun remove(Key: String?) {
        val editor = sharedpreferences!!.edit()
        editor.remove(Key)
        editor.commit()
    }

    fun removealldata() {
        val edit = sharedpreferences!!.edit()
        edit.clear().commit()
    }

}