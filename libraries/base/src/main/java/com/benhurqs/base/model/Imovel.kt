package com.benhurqs.base.model

import com.benhurqs.base.model.address.Address

class Imovel {
    var usableAreas: Double = 0.0
    var listingType: String? = null
    var createdAt: String? = null
    var listingStatus: String? = null
    var id: String? = null
    var parkingSpaces: Int = 0
    var updatedAt: String? = null
    var owner: Boolean = false
    var bathrooms: Int = 0
    var bedrooms: Int = 0
    var pricingInfos: PricingInfo? = null
    var address: Address? = null
    var images: Array<String>? = null


}