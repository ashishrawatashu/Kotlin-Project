package com.app.petitionatlas.mvp.search_voter_mvp.presentor

import com.app.petitionatlas.mvp.search_voter_mvp.model.SearchVoterInteractor
import com.app.petitionatlas.mvp.search_voter_mvp.model.SearchVoterInteractorImpl
import com.app.petitionatlas.mvp.search_voter_mvp.view.SearchVoterView
import com.app.petitionatlas.params.SearchvoterParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

class SearchVoterPresentorImpl:SearchVoterPresentor,SearchVoterInteractor.OnSearchVoterFinishListener {

    var searchvoterview: SearchVoterView? = null
    var searchVoterInteractorImpl: SearchVoterInteractorImpl? = null
    override fun attachview(
        searchvoterview: SearchVoterView?,
        searchVoterInteractorImpl: SearchVoterInteractorImpl?
    ) {
        this.searchvoterview = searchvoterview
        this.searchVoterInteractorImpl=searchVoterInteractorImpl
    }

    override fun detachview() {
        this.searchvoterview = null
        this.searchVoterInteractorImpl=null
    }

    override fun searchvoter(string: String?, searchvoterParams: SearchvoterParams?) {
        this.searchvoterview?.onSearchvotershowprogress()
        this.searchVoterInteractorImpl?.searchvoter(string,searchvoterParams,this)
    }

    override fun OnSearchVoterSuccess(streetresponse: ArrayList<StreetResponse>?) {
        this.searchvoterview?.onSearchvoterhideprogress()
        this.searchvoterview?.OnSearchvoterSuccess(streetresponse)
    }

    override fun OnSearchVoterFailure(error: String?) {
        this.searchvoterview?.onSearchvoterhideprogress()
        this.searchvoterview?.OnSearchvoterFailure(error)
    }
}