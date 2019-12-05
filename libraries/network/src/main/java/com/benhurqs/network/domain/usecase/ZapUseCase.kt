package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel

class ZapUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){

    /**
     *
    Ele apenas é elegível pro portal ZAP:
    Quando for aluguel e no mínimo o valor for de R$ 3.500,00.

     *
     */
    override fun rentalCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.RENTAL.name, true) &&
                imovel.pricingInfos!!.rentalTotalPrice >= 3500
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

    //TODO entender essa condição
    private fun usableAreasCondition(imovel: Imovel): Boolean{
        return imovel.usableAreas > 0
    }

    /**
     * Quando for venda e no mínimo o valor for de R$ 600.000,00.
     * Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP (descrito abaixo) considere a regra de valor mínimo (do imóvel) 10% menor.
     */
    private fun salesBoundingBoxCondition(imovel: Imovel): Boolean{
        val MIN_VALUE = 600000
        return if(insideBoundingBox(imovel.address?.geoLocation?.location!!)){
            imovel.pricingInfos!!.price  >= MIN_VALUE * (0.9)
        }else{
            imovel.pricingInfos!!.price >= MIN_VALUE
        }
    }
}