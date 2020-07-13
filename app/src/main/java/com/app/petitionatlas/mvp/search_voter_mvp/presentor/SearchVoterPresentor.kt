package com.app.petitionatlas.mvp.search_voter_mvp.presentor

import com.app.petitionatlas.mvp.search_voter_mvp.model.SearchVoterInteractorImpl
import com.app.petitionatlas.mvp.search_voter_mvp.view.SearchVoterView
import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.view.StreetView
import com.app.petitionatlas.params.SearchvoterParams
import com.app.petitionatlas.params.StreetParams

interface SearchVoterPresentor {

    fun attachview(searchvoterview: SearchVoterView?, searchVoterInteractorImpl: SearchVoterInteractorImpl?)
    fun detachview()
    fun searchvoter(string:String?,searchvoterParams: SearchvoterParams?)
}