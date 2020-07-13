package com.app.petitionatlas.params

import java.io.Serializable

class StreetParams :Serializable {

   lateinit var county: String
    lateinit var city: String
    lateinit var street: String
    lateinit var ward: String
    lateinit var precinct: String
    var startBlock: Int = 0
    var endBlock: Int = 0
}