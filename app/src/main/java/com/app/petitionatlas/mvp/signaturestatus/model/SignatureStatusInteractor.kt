package com.app.petitionatlas.mvp.supportvoter_mvp.model

import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams

interface SignatureStatusInteractor {

    fun signaturestatus(voterid:String?, signaturestatusParams: SignaturestatusParams?, onFinishListener: OnSupportVoterFinishListener?)
    interface OnSupportVoterFinishListener {
        fun OnSignaturestatusSuccess(string:String?)
        fun OnSignaturestatusFailure(error: String?)
    }
}