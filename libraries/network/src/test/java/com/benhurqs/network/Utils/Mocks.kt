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

        list.add(getImovel())
        return list
    }

    fun getImovel(): Imovel{
        return Imovel().apply {
            usableAreas = 12456.0
            address = getAddress()
            pricingInfos = getPricingInfoRental()
        }
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
            location = getLocationInsideBox()
        }
    }



    fun getPricingInfoRental(): PricingInfo {
        return PricingInfo().apply{
            price = 4000.0
            businessType = BusinessType.RENTAL.name
            monthlyCondoFee = "500"
        }
    }

    fun getPricingInfoSales(): PricingInfo {
        return PricingInfo().apply{
            price = 600000.0
            businessType = BusinessType.SALE.name
        }
    }

    fun getLocationInsideBox():Location{
        return Location().apply {
            lat = -23.558704
            lon = -46.673419

        }
    }

    fun getLocationOutBox(): Location {
        return Location().apply {
            lat = 10.0
            lon = -20.0
        }
    }
}