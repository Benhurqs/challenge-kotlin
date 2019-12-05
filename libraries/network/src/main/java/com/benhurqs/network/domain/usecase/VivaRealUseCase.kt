package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel

class VivaRealUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){

    /**
     *
        Ele apenas é elegível pro portal Viva Real:
        Quando for aluguel e no máximo o valor for de R$ 4.000,00.
        Quando for venda e no máximo o valor for de R$ 700.000,00.
     *
     */
    override fun rentalCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.RENTAL.name, true) && imovel.pricingInfos!!.rentalTotalPrice <= 4000
    }

    override fun salesCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.SALE.name, true) && imovel.pricingInfos!!.price <= 700000
    }
}