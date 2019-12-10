package com.benhurqs.network.domain.usecase.usacase

import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.address.Location
import com.benhurqs.network.domain.usecase.ListUseCase

class TestUseCaseHelper(cachedList: List<Imovel>) : ListUseCase(cachedList){
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