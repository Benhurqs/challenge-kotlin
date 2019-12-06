package com.benhurqs.network.domain.usecase.zap

import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.usecase.ZapUseCase

class TestZapUseCaseHelper(list: List<Imovel>): ZapUseCase(list){
    override fun rentalCondition(imovel: Imovel): Boolean {
        return super.rentalCondition(imovel)
    }

    override fun salesCondition(imovel: Imovel): Boolean {
        return super.salesCondition(imovel)
    }

    fun checkRentalCondition(imovel: Imovel): Boolean {
        return rentalCondition(imovel)
    }

    fun checksalesCondition(imovel: Imovel): Boolean {
        return salesCondition(imovel)
    }

    fun checkUsableAreasCondition(imovel: Imovel): Boolean {
        return usableAreasCondition(imovel)
    }

    fun checkSalesBoundingBoxCondition(imovel: Imovel): Boolean {
        return salesBoundingBoxCondition(imovel)
    }


}