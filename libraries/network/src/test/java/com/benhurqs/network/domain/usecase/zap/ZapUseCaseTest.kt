package com.benhurqs.network.domain.usecase.zap

import com.benhurqs.network.Utils.Mocks
import com.benhurqs.network.domain.model.BusinessType
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ZapUseCaseTest{
    lateinit var zapUseCase: TestZapUseCase

    @Before
    fun setUp(){
        zapUseCase =
            TestZapUseCase(Mocks.getListMock())
    }

    @Test
    fun `Check rental condition when price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 2500.0

        Assert.assertFalse(zapUseCase.checkRentalCondition(imovel))
    }


    @Test
    fun `Check rental condition when price is higher`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 3500.0

        Assert.assertTrue(zapUseCase.checkRentalCondition(imovel))
    }

    @Test
    fun `Check rental condition when business type is Sale`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name

        Assert.assertFalse(zapUseCase.checkRentalCondition(imovel))
    }

    @Test
    fun `Check usable area condition when area conditional is 0`(){
        val imovel = Mocks.getImovel()
        imovel.usableAreas = 0.0

        Assert.assertFalse(zapUseCase.checkUsableAreasCondition(imovel))
    }


    @Test
    fun `Check usable area condition when area conditional is not 0`(){
        val imovel = Mocks.getImovel()
        imovel.usableAreas = 10.0

        Assert.assertTrue(zapUseCase.checkUsableAreasCondition(imovel))
    }

    @Test
    fun `Check bounding box condition when location inside box and price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()
        imovel.pricingInfos?.price = 600000.0 * 0.80

        Assert.assertFalse(zapUseCase.checkSalesBoundingBoxCondition(imovel))
    }


    @Test
    fun `Check bounding box condition when location inside box and price is higher`(){
        val imovel = Mocks.getImovel()
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()
        imovel.pricingInfos?.price = 600000.0 * 0.91

        Assert.assertTrue(zapUseCase.checkSalesBoundingBoxCondition(imovel))
    }

    @Test
    fun `Check bounding box condition when location out box and price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.address?.geoLocation?.location = Mocks.getLocationOutBox()
        imovel.pricingInfos?.price = 600000.0 * 0.91

        Assert.assertFalse(zapUseCase.checkSalesBoundingBoxCondition(imovel))
    }


    @Test
    fun `Check bounding box condition when location out box and price is higher`(){
        val imovel = Mocks.getImovel()
        imovel.address?.geoLocation?.location = Mocks.getLocationOutBox()
        imovel.pricingInfos?.price = 600000.0 * 1.1

        Assert.assertTrue(zapUseCase.checkSalesBoundingBoxCondition(imovel))
    }



    @Test
    fun `Check sales condition when business type is Rental`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.RENTAL.name

        Assert.assertFalse(zapUseCase.checksalesCondition(imovel))
    }

    @Test
    fun `Check sales condition`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        imovel.usableAreas = 10.0
        imovel.pricingInfos?.price = 2000000.0

        Assert.assertTrue(zapUseCase.checksalesCondition(imovel))
    }
}