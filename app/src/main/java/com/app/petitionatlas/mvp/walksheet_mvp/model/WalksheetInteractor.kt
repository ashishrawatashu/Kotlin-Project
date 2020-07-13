package com.app.petitionatlas.mvp.walksheet_mvp.model

import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse

interface WalksheetInteractor {
    fun walksheet(maincampainid: String?, onFinishListener: OnWalksheetFinishListener?)

    interface OnWalksheetFinishListener {
        fun OnSuccess(walksheetresponse: WalksheetResponse?)
        fun OnFailure(error: String?)
    }
}