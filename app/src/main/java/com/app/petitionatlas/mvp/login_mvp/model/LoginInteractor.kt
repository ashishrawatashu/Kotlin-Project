package com.app.petitionatlas.mvp.login_mvp.model

import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.loginreponse.LoginResponse

interface LoginInteractor {

    fun login(loginParams: LoginParams?, onFinishListener: OnFinishListener?)

    interface OnFinishListener {
        fun OnSuccess(loginResponse: LoginResponse?)
        fun OnFailure(error: String?)
    }

}