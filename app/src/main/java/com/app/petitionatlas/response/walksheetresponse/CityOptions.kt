package com.app.petitionatlas.response.walksheetresponse

class CityOptions {

    private lateinit var wardOptions: List<WardOptions?>

    private lateinit var name: String

    fun getWardOptions(): List<WardOptions?>? {
        return wardOptions
    }

    fun setWardOptions(wardOptions: List<WardOptions?>) {
        this.wardOptions = wardOptions
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return "ClassPojo [wardOptions = $wardOptions, name = $name]"
    }
}