package com.app.petitionatlas.response.loginreponse

class WardOptions {

    private lateinit var precinctNames: List<String?>

    private var name: String? = null

    fun getPrecinctNames(): List<String?>? {
        return precinctNames
    }

    fun setPrecinctNames(precinctNames: List<String?>) {
        this.precinctNames = precinctNames
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    override fun toString(): String {
        return "ClassPojo [precinctNames = $precinctNames, name = $name]"
    }

}