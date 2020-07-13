package com.app.petitionatlas.mvp.walksheet_mvp.model

import android.util.Log
import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalksheetInteractorImpl : WalksheetInteractor {
    override fun walksheet(
        maincampainid: String?,
        onFinishListener: WalksheetInteractor.OnWalksheetFinishListener?
    ) {
        val walksheetRespnsecall: Call<WalksheetResponse> =
            WebServiceFactory.instance!!.walksheet("walksheets/csv/precinct/options/" +maincampainid)
        walksheetRespnsecall.enqueue(object : Callback<WalksheetResponse> {
            override fun onFailure(call: Call<WalksheetResponse>, t: Throwable) {
                onFinishListener?.OnFailure("Error Occurred")
            }

            override fun onResponse(
                call: Call<WalksheetResponse>,
                response: Response<WalksheetResponse>
            ) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            Log.d("DATALOG", "" +  response . body ())
                            val walksheetResponse = response.body()
                            onFinishListener?.OnSuccess(walksheetResponse)
                        } else {
                            onFinishListener?.OnFailure("Data Not Found")
                        }
                    } else {
                        if (response.code() == 404) {
                            onFinishListener?.OnFailure("Wrong Main Campaign Id")
                        }
                    }
                } else {
                    onFinishListener?.OnFailure("Error Occurred")
                }
            }
        })
    }
}
