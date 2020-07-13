package com.app.petitionatlas.response.walksheetresponse

import CountyOptions


class WalksheetResponse {
    private lateinit var countyOptions: List<CountyOptions?>

    fun getCountyOptions(): List<CountyOptions?> {
        return countyOptions
    }

    fun setCountyOptions(countyOptions: List<CountyOptions?>) {
        this.countyOptions = countyOptions
    }

    override fun toString(): String {
        return "ClassPojo [countyOptions = $countyOptions]"
    }
}