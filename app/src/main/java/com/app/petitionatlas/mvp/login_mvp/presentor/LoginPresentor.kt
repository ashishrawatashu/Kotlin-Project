package com.app.petitionatlas.mvp.login_mvp.presentor

import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractorImpl
import com.app.petitionatlas.mvp.login_mvp.view.LoginView
import com.app.petitionatlas.params.LoginParams

interface LoginPresentor {

    fun attachview(loginView: LoginView?, loginInteractor: LoginInteractorImpl?)
    fun detachview()
    fun login(loginParams: LoginParams?)

}