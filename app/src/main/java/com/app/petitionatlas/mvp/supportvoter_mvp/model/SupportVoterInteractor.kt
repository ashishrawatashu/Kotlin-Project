package com.app.petitionatlas.mvp.supportvoter_mvp.model

import com.app.petitionatlas.params.votersupportparams.VoterSupportParams

interface SupportVoterInteractor {

    fun supportvoter(voterid:String?, voterSupportParams: VoterSupportParams?, onFinishListener: OnSupportVoterFinishListener?)
    interface OnSupportVoterFinishListener {
        fun OnSupportVoterSuccess(string:String?)
        fun OnSupportVoterFailure(error: String?)
    }
}