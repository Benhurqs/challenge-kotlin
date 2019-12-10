package com.benhurqs.base.model

import java.io.Serializable

class PricingInfo : Serializable{
    var price: Double = 0.0 // Valor de venda
    var rentalTotalPrice: Double = 0.0 // Valor de aluguel
    var businessType: String? = null // define se é aluguel ou venda
    var yearlyIptu: Double = 0.0
    var monthlyCondoFee: String? = null
}