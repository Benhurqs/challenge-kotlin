package com.benhurqs.network.domain.model

import java.io.Serializable

class PricingInfo : Serializable{
    var price: Double = 0.0 // Valor de venda
    var rentalTotalPrice: Double = 0.0 // Valor de aluguel
    var businessType: String? = null // define se é aluguel ou venda
    var yearlyIptu: Double = 0.0
    var monthlyCondoFee: Double = 0.0
}