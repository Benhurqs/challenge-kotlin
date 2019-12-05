package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel

class ZapUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){

    /**
     *
    Ele apenas é elegível pro portal ZAP:
    Quando for aluguel e no mínimo o valor for de R$ 3.500,00.
    Quando for venda e no mínimo o valor for de R$ 600.000,00.
     *
     */

    override fun rentalCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.RENTAL.name, true) && imovel.pricingInfos!!.rentalTotalPrice >= 3500
    }

    override fun salesCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.SALE.name, true) && imovel.pricingInfos!!.price >= 600000
    }
}