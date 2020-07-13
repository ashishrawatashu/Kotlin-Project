package com.app.petitionatlas.mvp.walksheetstreetmvp.model

import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

interface StreetInteractor {
    fun street(maincampaignid:String?, streetparams: StreetParams?, onFinishListener: OnStreetFinishListener?)

    interface OnStreetFinishListener {
        fun OnStreetSuccess(streetresponse: ArrayList<StreetResponse>?)
        fun OnStreetFailure(error: String?)
    }
}