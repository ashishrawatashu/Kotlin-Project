package com.app.petitionatlas.mvp.search_voter_mvp.model

import com.app.petitionatlas.activities.SearchVoter
import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.params.SearchvoterParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

interface SearchVoterInteractor {

    fun searchvoter(maincampaignid:String?,searchvoterParams: SearchvoterParams?, onFinishListener: OnSearchVoterFinishListener?)

    interface OnSearchVoterFinishListener {
        fun OnSearchVoterSuccess(streetresponse: ArrayList<StreetResponse>?)
        fun OnSearchVoterFailure(error: String?)
    }

}