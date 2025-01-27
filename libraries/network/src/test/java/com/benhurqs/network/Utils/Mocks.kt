package com.benhurqs.network.Utils

import com.benhurqs.base.model.BusinessType
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.PricingInfo
import com.benhurqs.base.model.address.Address
import com.benhurqs.base.model.address.Geolocation
import com.benhurqs.base.model.address.Location

object Mocks{

    fun getListMock(): List<Imovel>{
        var list = ArrayList<Imovel>()

        list.add(getImovel())
        return list
    }

    fun getImovel(): Imovel{
        return Imovel().apply {
            usableAreas = 12456f
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
            price = 4000f
            businessType = BusinessType.RENTAL.name
            monthlyCondoFee = "500"
        }
    }

    fun getPricingInfoSales(): PricingInfo {
        return PricingInfo().apply{
            price = 600000f
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

    fun getSalesZapImovel(): Imovel{
        val imovel = Mocks.getImovel()
        imovel.id = "1"
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        imovel.usableAreas = 10f
        imovel.pricingInfos?.price = 800000f

        return imovel
    }


    fun getRentalZapImovel(): Imovel{
        val imovel = Mocks.getImovel()
        imovel.id = "2"
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 7000f

        return imovel
    }

    fun getSalesVivaImovel(): Imovel{
        val imovel = Mocks.getImovel()
        imovel.id = "3"
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        imovel.pricingInfos?.price = 500000f

        return imovel
    }


    fun getRentalVivaRealImovel(): Imovel{
        val imovel = Mocks.getImovel()
        imovel.id = "4"
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.monthlyCondoFee = "100"
        imovel.pricingInfos?.price = 1000f

        return imovel
    }
}