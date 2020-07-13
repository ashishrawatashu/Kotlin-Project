package com.app.petitionatlas.mvp.supportvoter_mvp.view

import com.app.petitionatlas.response.supportVoterResponse.SupportVoterResponse

interface SupportVoterView {

    fun onSupportVoterSuccess(string:String?)
    fun onSupportVoterFailure(error: String?)
    fun onSupportVotershowprogress()
    fun onSupportVoterhideprogress()

}