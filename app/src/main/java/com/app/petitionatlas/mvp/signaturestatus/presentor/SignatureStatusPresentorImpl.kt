package com.app.petitionatlas.mvp.supportvoter_mvp.presentor
import com.app.petitionatlas.mvp.signaturestatus.view.SignaturestatusView
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractor
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SupportVoterInteractor
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.view.SupportVoterView
import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
class SignatureStatusPresentorImpl :SignaturestatusPresentor,SignatureStatusInteractor.OnSupportVoterFinishListener {

    var signatureStatusInteractorImpl:SignatureStatusInteractorImpl?=null
    var signaturestatusView:SignaturestatusView?=null
    override fun attachview(
        signaturestatusView: SignaturestatusView?,
        signatureStatusInteractorImpl: SignatureStatusInteractorImpl
    ) {
        this.signatureStatusInteractorImpl = signatureStatusInteractorImpl
        this.signaturestatusView = signaturestatusView}

    override fun dettachview() {
        this.signatureStatusInteractorImpl = null
        this.signaturestatusView = null
    }

    override fun signaturestatus(voterid: String?, signaturestatusParams: SignaturestatusParams) {
        this.signaturestatusView?.onSignaturestatusshowprogress()
        this.signatureStatusInteractorImpl?.signaturestatus(voterid,signaturestatusParams,this)
    }

    override fun OnSignaturestatusSuccess(string: String?) {
        this.signaturestatusView?.onSigntaurestatushideprogress()
        this.signaturestatusView?.onSignaturestatusSuccess(string)
    }

    override fun OnSignaturestatusFailure(error: String?) {
        this.signaturestatusView?.onSigntaurestatushideprogress()
        this.signaturestatusView?.onSignaturestatusFailure(error)
    }
}