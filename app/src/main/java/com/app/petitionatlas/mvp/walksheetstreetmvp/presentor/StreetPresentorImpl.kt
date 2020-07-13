package com.app.petitionatlas.mvp.walksheetstreetmvp.presentor

import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractor
import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.view.StreetView
import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

class StreetPresentorImpl : StreetPresentor,StreetInteractor.OnStreetFinishListener{

    var streetview: StreetView? = null
    var streetInteractorImpl: StreetInteractorImpl? = null
    override fun attachview(streetview: StreetView?, streetInteractorImpl: StreetInteractorImpl?) {
        this.streetview = streetview
        this.streetInteractorImpl=streetInteractorImpl
    }

    override fun detachview() {
        this.streetview = null
        this.streetInteractorImpl=null
    }

    override fun street(string: String?, streetparams: StreetParams?) {
            this.streetview?.onStreetshowprogress()
            this.streetInteractorImpl?.street(string,streetparams,this)
    }

    override fun OnStreetSuccess(streetresponse: ArrayList<StreetResponse>?) {
        this.streetview?.onStreethideprogress()
        this.streetview?.OnStreetSuccess(streetresponse)
    }

    override fun OnStreetFailure(error: String?) {
        this.streetview?.onStreethideprogress()
        this.streetview?.OnStreetFailure(error)
    }

}