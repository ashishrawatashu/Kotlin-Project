package com.app.petitionatlas.mvp.supportvoter_mvp.model

import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupportVoterInteractorImpl : SupportVoterInteractor {
    override fun supportvoter(
        voterid: String?,
        voterSupportParams: VoterSupportParams?,
        onFinishListener: SupportVoterInteractor.OnSupportVoterFinishListener?
    ) {
        val supportresponsecall: Call<Void> =
            WebServiceFactory.instance!!.supportvoter(
                "voters/support/" + voterid,
                voterSupportParams
            )
        supportresponsecall.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFinishListener?.OnSupportVoterFailure("Error Occurred")
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            onFinishListener?.OnSupportVoterSuccess("Data Save Successfully")
                        } else {
                            onFinishListener?.OnSupportVoterFailure("Some Thing Went Wrong")
                        }
                    } else {
                        if (response.code() == 404) {
                            onFinishListener?.OnSupportVoterFailure("Wrong Voter Id")
                        } else if (response.code() == 500) {
                            onFinishListener?.OnSupportVoterFailure("Internal Server Error")
                        }
                    }
                } else {
                    onFinishListener?.OnSupportVoterFailure("Some Thing Went Wrong")
                }
            }
        })
    }
}