package com.benhurqs.base.model.address

import java.io.Serializable

class Address : Serializable{
    var city: String? = null
    var neighborhood: String? = null
    var geoLocation: Geolocation? = null
}