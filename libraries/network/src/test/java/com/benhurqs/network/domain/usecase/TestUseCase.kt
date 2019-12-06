package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.model.address.Location

class TestUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){
    override fun rentalCondition(imovel: Imovel): Boolean {
        return true
    }

    override fun salesCondition(imovel: Imovel): Boolean {
        return true
    }

    fun checkBoundingBox(location: Location): Boolean{
        return insideBoundingBox(location)
    }
}