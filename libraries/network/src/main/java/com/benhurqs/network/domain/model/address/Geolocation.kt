package com.benhurqs.network.domain.model.address

import java.io.Serializable

class Geolocation : Serializable{
    var precision: String? = null
    var location: Location? = null
}