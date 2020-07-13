package com.app.petitionatlas.response.loginreponse

import com.app.petitionatlas.response.loginreponse.CityOptions

class CountyOptions {

    private lateinit var cityOptions: List<CityOptions?>

    private var name: String? = null

    fun getCityOptions(): List<CityOptions?>? {
        return cityOptions
    }

    fun setCityOptions(cityOptions: List<CityOptions?>) {
        this.cityOptions = cityOptions
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    override fun toString(): String {
        return "ClassPojo [cityOptions = $cityOptions, name = $name]"
    }

}