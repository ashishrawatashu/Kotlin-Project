package com.app.petitionatlas.mvp.walksheetstreetmvp.model

import android.util.Log
import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StreetInteractorImpl :StreetInteractor {
    override fun street(
        maincampaignid: String?,
        streetparams: StreetParams?,
        onFinishListener: StreetInteractor.OnStreetFinishListener?
    ) {
        val streetRespnsecall: Call<ArrayList<StreetResponse>> =
            WebServiceFactory.instance!!.street("walksheets/mobile/street/"+maincampaignid,
                streetparams?.county.toString(), streetparams?.city.toString(), streetparams?.street.toString(),
                streetparams?.ward.toString(), streetparams?.precinct.toString(),streetparams?.startBlock!!,streetparams?.endBlock!!)

        streetRespnsecall.enqueue(object : Callback<ArrayList<StreetResponse>>{
            override fun onFailure(call: Call<ArrayList<StreetResponse>>, t: Throwable) {
                Log.d("DATALOG", "chrck5=> "+t)
                onFinishListener?.OnStreetFailure("Error Occurred")
            }

            override fun onResponse(
                call: Call<ArrayList<StreetResponse>>,
                response: Response<ArrayList<StreetResponse>>
            ) {
                if(response!= null){
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            val walksheetResponse = response.body()

                            Log.d("DATALOG", "chrck1" + response.body())
                            onFinishListener?.OnStreetSuccess(walksheetResponse)
                        } else {
                            Log.d("DATALOG", "chrck2")
                            onFinishListener?.OnStreetFailure("Error Occurred")
                        }
                    } else {
                        if (response.code() == 404) {
                            Log.d("DATALOG", "chrck3")
                            onFinishListener?.OnStreetFailure("Wrong Main Campaign Id")
                        }
                    }
                }else {
                    Log.d("DATALOG", "chrck4")
                    onFinishListener?.OnStreetFailure("Error Occurred")
                }
            }
        })
    }
}