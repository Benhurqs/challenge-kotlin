package com.benhurqs.network.domain.usecase

import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel

open class VivaRealUseCase(cachedList: List<Imovel>) : ListUseCase(cachedList){

    override fun rentalCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.RENTAL.name, true) &&
                rentalCondoFeeCondition(imovel) &&
                rentalBoundingBoxCondition(imovel)
    }

    /**
     *
    Caso o imóvel seja para aluguel, ele é elegível para o portal Viva Real se:
    O valor do condomínio não pode ser maior/igual que 30% do valor do aluguel - apenas aplicado para imóveis que tenham um monthlyCondoFee válido e numérico (imóveis com monthlyCondoFee não numérico ou inválido não são elegíveis).
     *
     */
    protected fun rentalCondoFeeCondition(imovel: Imovel): Boolean{
        val monthlyCondoFee = imovel.pricingInfos?.monthlyCondoFee
        if(monthlyCondoFee == null || !monthlyCondoFee.matches("-?\\d+(\\.\\d+)?".toRegex())){
            return false
        }

        val monthlyCondoFeeValue = monthlyCondoFee.toFloat()
        if(monthlyCondoFeeValue <= 0 ){
            return false
        }

        return (monthlyCondoFeeValue < imovel.pricingInfos!!.price*(0.3))
    }

    /**
     *
    Quando for aluguel e no máximo o valor for de R$ 4.000,00.
    Quando o imóvel estiver dentro do bounding box dos arredores do Grupo ZAP (descrito abaixo) considere a regra de valor máximo (do aluguel do imóvel) 50% maior.
     *
     */
    protected fun rentalBoundingBoxCondition(imovel: Imovel): Boolean{
        var MAX_PRICE = 4000.0
        if(insideBoundingBox(imovel.address?.geoLocation?.location!!)){
            return imovel.pricingInfos!!.price  <= MAX_PRICE * (1.5)
        }else{
            return imovel.pricingInfos!!.price <= MAX_PRICE
        }

    }


    /**
     *
    Ele apenas é elegível pro portal Viva Real:
    Quando for venda e no máximo o valor for de R$ 700.000,00.
     *
     */
    override fun salesCondition(imovel: Imovel): Boolean {
        return imovel.pricingInfos!!.businessType!!.equals(BusinessType.SALE.name, true) &&
                imovel.pricingInfos!!.price <= 700000
    }
}