package com.benhurqs.base.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.benhurqs.base.R
import com.benhurqs.base.model.BusinessType
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.PricingInfo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImovelFormatedUtilsTest{

    lateinit var context: Context

    @Before
    fun setUp(){
        context = InstrumentationRegistry.getInstrumentation().getTargetContext()
    }

    @Test
    fun test_description_formatter(){
        var bedrooms = 1
        var bathrooms = 2
        var usableAreas = 22.3f
        var parkingSpaces = 3

        val imovel = Imovel().apply {
            this.bedrooms = bedrooms
            this.bathrooms = bathrooms
            this.usableAreas = usableAreas
            this.parkingSpaces = parkingSpaces
        }

        var description = ImovelFormatedUtils.getDescription(context, imovel)
        var descriptionMock = context.getString(R.string.description, bedrooms, bathrooms, parkingSpaces, usableAreas.toString())
        Assert.assertEquals(description, descriptionMock)
    }

    @Test
    fun test_price_formatter(){
        val imovel = Imovel().apply {
            pricingInfos = PricingInfo().apply {
                price = 123.56f
            }
        }

        var price = ImovelFormatedUtils.formatPrice(context, imovel)
        var priceMock = "R$ 123,56"
        Assert.assertEquals(price, priceMock)

    }

    @Test
    fun test_business_type_rental_formatter(){
        testBusinessType(BusinessType.RENTAL.name, R.string.rental)
    }

    @Test
    fun test_business_type_sales_formatter(){
        testBusinessType(BusinessType.SALE.name, R.string.sales)
    }

    private fun testBusinessType(type: String, stringId: Int){
        val imovel = Imovel().apply {
            pricingInfos = PricingInfo().apply {
                businessType = type
            }
        }

        var type = ImovelFormatedUtils.getBusinessType(context, imovel)
        var typeMock = context.getString(stringId)
        Assert.assertEquals(type, typeMock)
    }

}