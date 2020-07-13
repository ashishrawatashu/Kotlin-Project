package com.app.petitionatlas.mvp.walksheetstreetmvp.presentor

import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.view.StreetView
import com.app.petitionatlas.params.StreetParams

interface StreetPresentor {
    fun attachview(streetview: StreetView?, streetInteractorImpl: StreetInteractorImpl?)
    fun detachview()
    fun street(string:String?,streetparams: StreetParams?)
}