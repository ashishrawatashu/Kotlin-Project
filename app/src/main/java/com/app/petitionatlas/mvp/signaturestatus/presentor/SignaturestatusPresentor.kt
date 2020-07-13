package com.app.petitionatlas.mvp.supportvoter_mvp.presentor

import com.app.petitionatlas.mvp.signaturestatus.view.SignaturestatusView
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.view.SupportVoterView
import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams

interface SignaturestatusPresentor {
    fun attachview(signaturestatusView: SignaturestatusView?, signatureStatusInteractorImpl: SignatureStatusInteractorImpl)
    fun dettachview()
    fun signaturestatus(voterid:String?,signaturestatusParams: SignaturestatusParams)
}