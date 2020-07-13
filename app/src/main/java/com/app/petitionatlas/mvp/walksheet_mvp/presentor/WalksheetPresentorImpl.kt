package com.app.petitionatlas.mvp.walksheet_mvp.presentor

import com.app.petitionatlas.mvp.walksheet_mvp.model.WalksheetInteractor
import com.app.petitionatlas.mvp.walksheet_mvp.model.WalksheetInteractorImpl
import com.app.petitionatlas.mvp.walksheet_mvp.view.WalksheetView
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse

class WalksheetPresentorImpl : WalksheetPresentor, WalksheetInteractor.OnWalksheetFinishListener  {
    var walksheetview: WalksheetView? = null
    var walksheetInteractorImpl: WalksheetInteractorImpl? = null

    override fun OnSuccess(walksheetresponse: WalksheetResponse?) {
        walksheetview?.hideprogress()
        walksheetview?.OnSuccess(walksheetresponse)
    }

    override fun OnFailure(error: String?) {
        walksheetview?.hideprogress()
        walksheetview?.OnFailure(error)
    }

    override fun attachview(walksheetView: WalksheetView?, walksheetInteractorImpl: WalksheetInteractorImpl?) {
        this.walksheetview = walksheetView
        this.walksheetInteractorImpl=walksheetInteractorImpl
    }

    override fun dettachview() {
        this.walksheetview = null
        this.walksheetInteractorImpl=null
    }

    override fun walksheet(maincampaignid: String?) {
        this.walksheetview?.showprogress()
        this.walksheetInteractorImpl?.walksheet(maincampaignid,this)
    }
}