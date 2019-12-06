package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.model.address.Geolocation
import com.benhurqs.network.domain.model.address.Location

abstract class ListUseCase(cachedList: List<Imovel>){

    protected var formatedList: List<Imovel>? = null

    init {
        execute(cachedList)
    }

    fun getList() = formatedList

    protected abstract fun rentalCondition(imovel: Imovel): Boolean
    protected abstract fun salesCondition(imovel: Imovel): Boolean

    /**
     *
        Um imóvel não é elegível em NENHUM PORTAL se:
        Ele tem lat e lon iguais a 0.
     *
     */
    private fun execute(cachedList: List<Imovel>){
        formatedList = cachedList.filter {imovel ->
            geolocationCondition(imovel.address?.geoLocation) &&
            imovel.pricingInfos?.businessType != null &&
                    ( rentalCondition(imovel) || salesCondition(imovel) )
        }
    }

    private fun geolocationCondition(geolocation: Geolocation?): Boolean{
        return geolocation?.location != null && geolocation.location!!.lat != 0.0 && geolocation.location!!.lon != 0.0
    }

    protected fun insideBoundingBox(location: Location): Boolean{
        val minlon = -46.693419
        val minlat = -23.568704
        val maxlon = -46.641146
        val maxlat = -23.546686

        return (location.lat in minlat..maxlat && location.lon in minlon..maxlon)
    }


}