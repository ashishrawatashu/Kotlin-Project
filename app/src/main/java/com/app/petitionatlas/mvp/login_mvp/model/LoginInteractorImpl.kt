package com.app.petitionatlas.mvp.login_mvp.model

import android.util.Log
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImpl : LoginInteractor {

    override fun login(
        loginParams: LoginParams?,
        onFinishListener: LoginInteractor.OnFinishListener?
    ) {
        val loginResponseCall: retrofit2.Call<LoginResponse> = WebServiceFactory.instance!!.loginResponseCall(
            GlobalVariabels.CONTENT_TYPE,
            loginParams
        )
        loginResponseCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse?>?,
                response: Response<LoginResponse?>?
            ) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() === 200) {
                            val loginResponse = response.body()

                            Log.d("response123456", "chrck1" + response.body())
                            onFinishListener?.OnSuccess(loginResponse)
                        } else {
                            Log.d("response123456", "chrck2")
                            onFinishListener?.OnFailure("Error Occurred")
                        }
                    } else {
                        if (response.code() === 401) {
                            Log.d("response123456", "chrck3")
                            onFinishListener?.OnFailure("wrong username or password")
                        }
                    }
                } else {
                    Log.d("response123456", "chrck4")
                    onFinishListener?.OnFailure("Error Occurred")
                }
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse?>?, t: Throwable) {
                Log.d("response123456", "chrck5=> "+t)
                onFinishListener?.OnFailure(t.toString())
            }
        })
    }


}