package com.app.petitionatlas.mvp.login_mvp.view

import com.app.petitionatlas.response.loginreponse.LoginResponse

interface LoginView {

    fun OnSuccess(loginResponse: LoginResponse?)
    fun OnFailure(error: String?)
    fun showprogress()
    fun hideprogress()

}