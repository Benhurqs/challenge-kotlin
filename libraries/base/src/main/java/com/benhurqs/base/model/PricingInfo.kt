package com.benhurqs.base.model

import java.io.Serializable

class PricingInfo : Serializable{
    var price: Float = 0f // Valor de venda
    var rentalTotalPrice: Float = 0f // Valor de aluguel
    var businessType: String? = null // define se é aluguel ou venda
    var yearlyIptu: Float = 0f
    var monthlyCondoFee: String? = null
}