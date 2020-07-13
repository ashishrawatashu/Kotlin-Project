package com.app.petitionatlas.mvp.walksheetstreetmvp.view

import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse

interface StreetView {

    fun OnStreetSuccess(streetResponse: ArrayList<StreetResponse>?)
    fun OnStreetFailure(error: String?)
    fun onStreetshowprogress()
    fun onStreethideprogress()

}