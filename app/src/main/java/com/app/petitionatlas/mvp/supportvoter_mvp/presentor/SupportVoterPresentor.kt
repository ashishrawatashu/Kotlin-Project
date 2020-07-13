package com.app.petitionatlas.mvp.supportvoter_mvp.presentor

import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SupportVoterInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.view.SupportVoterView
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams

interface SupportVoterPresentor {
    fun attachview(supportVoterView: SupportVoterView?, supportVoterInteractorImpl: SupportVoterInteractorImpl?)
    fun dettachview()
    fun supportvoter(voterid:String?,supportVoterParams: VoterSupportParams)
}
