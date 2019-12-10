package com.benhurqs.network.domain.usecase

import com.benhurqs.base.model.BusinessType
import com.benhurqs.base.model.Imovel

open class ZapUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){

    /**
     *
    Ele apenas é elegível pro portal ZAP:
    Quando for aluguel e no mínimo o valor for de R$ 3.500,00.

     *
     */
    override fun rentalCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.RENTAL.name, true) &&
                imovel.pricingInfos!!.price >= 3500
    }


    override fun salesCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.SALE.name, true) &&
                usableAreasCondition(imovel) &&
                salesBoundingBoxCondition(imovel)
    }

    /**
     *
    Caso o imóvel seja para venda, ele é elegível para o portal ZAP se:
    O valor do metro quadrado (chave usableAreas) não pode ser menor/igual a R$ 3.500,00 - apenas considerando imóveis que tenham usableAreas acima de 0 (imóveis com usableAreas = 0 não são elegíveis).

     */
    protected fun usableAreasCondition(imovel: Imovel): Boolean{
        if(imovel.usableAreas > 0){
            val usablePrice = imovel.pricingInfos!!.price / imovel.usableAreas
            return usablePrice > 3500
        }

        return false

    }

    /**
     * Quando for venda e no mínimo o valor for de R$ 600.000,00.
     * Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP (descrito abaixo) considere a regra de valor mínimo (do imóvel) 10% menor.
     */
    protected fun salesBoundingBoxCondition(imovel: Imovel): Boolean{
        val MIN_VALUE = 600000
        return if(insideBoundingBox(imovel.address?.geoLocation?.location!!)){
            imovel.pricingInfos!!.price  >= MIN_VALUE * (0.9)
        }else{
            imovel.pricingInfos!!.price >= MIN_VALUE
        }
    }
}