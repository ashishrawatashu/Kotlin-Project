package com.app.petitionatlas.mvp.supportvoter_mvp.presentor

import com.app.petitionatlas.mvp.supportvoter_mvp.model.SupportVoterInteractor
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SupportVoterInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.view.SupportVoterView
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams

class SupportVoterPresentorImpl :SupportVoterPresentor,SupportVoterInteractor.OnSupportVoterFinishListener {

    var supportVoterInteractorImpl:SupportVoterInteractorImpl?=null
    var supportVoterView:SupportVoterView?=null

    override fun attachview(
        supportVoterView: SupportVoterView?,
        supportVoterInteractorImpl: SupportVoterInteractorImpl?
    ) {
        this.supportVoterInteractorImpl=supportVoterInteractorImpl
        this.supportVoterView= supportVoterView
    }

    override fun dettachview() {
        this.supportVoterInteractorImpl=null
        this.supportVoterView= null
    }

    override fun supportvoter(voterid:String?,supportVoterParams: VoterSupportParams) {
        this.supportVoterView?.onSupportVotershowprogress()
        this.supportVoterInteractorImpl?.supportvoter(voterid,supportVoterParams,this)
    }

    override fun OnSupportVoterSuccess(string:String?) {
        this.supportVoterView?.onSupportVoterhideprogress()
        this.supportVoterView?.onSupportVoterSuccess(string)
    }

    override fun OnSupportVoterFailure(error: String?) {
        this.supportVoterView?.onSupportVoterhideprogress()
        this.supportVoterView?.onSupportVoterFailure(error)
    }
}