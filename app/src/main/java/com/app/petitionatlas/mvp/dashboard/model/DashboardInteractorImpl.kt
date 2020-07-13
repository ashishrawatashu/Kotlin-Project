package com.app.petitionatlas.mvp.dashboard.model

import android.util.Log
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.retrofit.WebServiceFactory
import com.app.petitionatlas.sharedvalues.SharedValues
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardInteractorImpl : DashboardInteractor {
    override fun dashboard(id: String?, onFinishListener: DashboardInteractor.OnFinishListener?) {
        Log.d("DATALOG","campaigns/mobile/dashboard/"+id);
        val dashboardresponsecall: Call<DashboardResponse> =

            WebServiceFactory.instance!!.dashboard("campaigns/mobile/dashboard/"+id)
        dashboardresponsecall.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            val dashboardResponse = response.body()

                            Log.d("response123456", "chrck1" + response.body())
                            onFinishListener?.OndashboardSuccess(dashboardResponse)
                        } else {
                            Log.d("response123456", "chrck2")
                            onFinishListener?.OndashboardFailure("Error Occurred")
                        }
                    } else {
                        if (response.code() == 404) {
                            Log.d("response123456", "chrck3")
                            onFinishListener?.OndashboardFailure("Wrong Main Campaign Id")
                        }
                    }
                } else {
                    Log.d("response123456", "chrck4")
                    onFinishListener?.OndashboardFailure("Error Occurred")
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                onFinishListener?.OndashboardFailure("Error Occurred")
            }

        })
    }
}