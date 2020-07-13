package com.app.petitionatlas.mvp.search_voter_mvp.view

import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

interface SearchVoterView {

    fun OnSearchvoterSuccess(searchvoterresponse: ArrayList<StreetResponse>?)
    fun OnSearchvoterFailure(error: String?)
    fun onSearchvotershowprogress()
    fun onSearchvoterhideprogress()

}