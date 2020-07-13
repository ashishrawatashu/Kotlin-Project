package com.app.petitionatlas.mvp.search_voter_mvp.model

import android.util.Log
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.params.SearchvoterParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchVoterInteractorImpl:SearchVoterInteractor {

    override fun searchvoter(
        maincampaignid: String?,
        searchvoterParams: SearchvoterParams?,
        onFinishListener: SearchVoterInteractor.OnSearchVoterFinishListener?
    ) {
        val searchvoterResponsecall: Call<ArrayList<StreetResponse>> =
            WebServiceFactory.instance!!.searchvoter("voters/"+maincampaignid,
                searchvoterParams?.firstname.toString(), searchvoterParams?.lastname.toString(), searchvoterParams?.street.toString(),
                searchvoterParams?.housenumber.toString())

        searchvoterResponsecall.enqueue(object :Callback<ArrayList<StreetResponse>>{
            override fun onFailure(call: Call<ArrayList<StreetResponse>>, t: Throwable) {
                onFinishListener?.OnSearchVoterFailure("Error Occurred3")
                Log.d("DATALOG","check=> "+t.message)
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
                            onFinishListener?.OnSearchVoterSuccess(walksheetResponse)
                        } else {
                            Log.d("DATALOG", "chrck2")
                            onFinishListener?.OnSearchVoterFailure("Error Occurred")
                        }
                    } else {
                        if (response.code() == 404) {
                            Log.d("DATALOG", "chrck3")
                            onFinishListener?.OnSearchVoterFailure("Wrong Main Campaign Id")
                        }
                    }
                }else {
                    Log.d("DATALOG", "chrck4")
                    onFinishListener?.OnSearchVoterFailure("Error Occurred")
                }
            }
        })
    }
}