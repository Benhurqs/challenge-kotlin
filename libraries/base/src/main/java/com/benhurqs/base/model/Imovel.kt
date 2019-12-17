package com.benhurqs.base.model

import com.benhurqs.base.model.address.Address
import java.io.Serializable

class Imovel: Serializable {
    var usableAreas: Float = 0f
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
    var images: List<String>? = null


}