package com.app.petitionatlas.mvp.walksheet_mvp.presentor

import com.app.petitionatlas.mvp.walksheet_mvp.model.WalksheetInteractorImpl
import com.app.petitionatlas.mvp.walksheet_mvp.view.WalksheetView
import com.app.petitionatlas.params.LoginParams

interface WalksheetPresentor {

    fun attachview(walksheetView: WalksheetView?, walksheetInteractorImpl: WalksheetInteractorImpl?)
    fun dettachview()
    fun walksheet(maincampaignid: String?)

}