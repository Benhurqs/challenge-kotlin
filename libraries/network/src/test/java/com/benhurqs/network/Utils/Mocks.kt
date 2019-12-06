package com.benhurqs.network.Utils

import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.model.PricingInfo
import com.benhurqs.network.domain.model.address.Address
import com.benhurqs.network.domain.model.address.Geolocation
import com.benhurqs.network.domain.model.address.Location

object Mocks{

    fun getListMock(): List<Imovel>{
        var list = ArrayList<Imovel>()

        var imovel = Imovel().apply {
            usableAreas = 12456.0
            address = getAddress()
            pricingInfos = getPricingInfo()
        }

        list.add(imovel)
        return list
    }

    fun getAddress(): Address {
        return Address().apply {
            city = "Cidade"
            neighborhood = "Bairro"
            geoLocation = getGeoLocation()

        }
    }

    fun getGeoLocation(): Geolocation {
        return Geolocation().apply {
            precision = "Precision"
            location = getLocation()
        }
    }

    fun getLocation(): Location {
        return Location().apply {
            lat = 10.0
            lon = -20.0
        }
    }

    fun getPricingInfo(value: Double = 700000.0,
                               monthly: String = "3500",
                               type: BusinessType = BusinessType.RENTAL): PricingInfo {

        return PricingInfo().apply{
            price = value
            businessType = type.name
            monthlyCondoFee = monthly
        }

    }
}