package com.app.petitionatlas.mvp.walksheet_mvp.view

import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse

interface WalksheetView {

    fun OnSuccess(walksheetResponse: WalksheetResponse?)
    fun OnFailure(error: String?)
    fun showprogress()
    fun hideprogress()

}