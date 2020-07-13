package com.app.petitionatlas.params

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginParams {
    @SerializedName("username")
    @Expose
    var name: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
}