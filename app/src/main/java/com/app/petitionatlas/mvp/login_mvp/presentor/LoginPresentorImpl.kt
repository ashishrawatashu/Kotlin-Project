package com.app.petitionatlas.mvp.login_mvp.presentor

import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractor
import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractorImpl
import com.app.petitionatlas.mvp.login_mvp.view.LoginView
import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.loginreponse.LoginResponse

class LoginPresentorImpl: LoginPresentor, LoginInteractor.OnFinishListener {
        var loginView: LoginView? = null
        var loginInteractor: LoginInteractorImpl? = null
        override fun OnSuccess(loginResponse: LoginResponse?) {
            loginView?.hideprogress()
            loginView?.OnSuccess(loginResponse)
        }

        override fun OnFailure(error: String?) {
            loginView?.hideprogress()
            loginView?.OnFailure(error)
        }

        override fun attachview(loginView: LoginView?, loginInteractor: LoginInteractorImpl?) {
            this.loginView = loginView
            this.loginInteractor = loginInteractor
        }

        override fun detachview() {
            loginView = null
            loginInteractor = null
        }

        override fun login(loginParams: LoginParams?) {
            loginView?.showprogress()
            loginInteractor?.login(loginParams, this)
        }



}