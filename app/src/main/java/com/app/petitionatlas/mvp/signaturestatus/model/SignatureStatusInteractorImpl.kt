package com.app.petitionatlas.mvp.supportvoter_mvp.model

import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign

class SignatureStatusInteractorImpl : SignatureStatusInteractor {
    override fun signaturestatus(
        voterid: String?,
        signaturestatusParams: SignaturestatusParams?,
        onFinishListener: SignatureStatusInteractor.OnSupportVoterFinishListener?
    ) {
        val signaturestatuscall: Call<Void> =
            WebServiceFactory.instance!!.signaturestatus(
                "voters/signature-status/" + voterid,
                signaturestatusParams
            )

        signaturestatuscall.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFinishListener?.OnSignaturestatusFailure("Error Occurred")
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            onFinishListener?.OnSignaturestatusSuccess("Submit")
                        } else {
                            onFinishListener?.OnSignaturestatusFailure("Some Thing Went Wrong")
                        }
                    } else {
                        if (response.code() == 404) {
                            onFinishListener?.OnSignaturestatusFailure("Wrong Voter Id")
                        } else if (response.code() == 500) {
                            onFinishListener?.OnSignaturestatusFailure("Internal Server Error")
                        }
                    }
                } else {
                    onFinishListener?.OnSignaturestatusFailure("Some Thing Went Wrong")
                }
            }
        })
    }
}