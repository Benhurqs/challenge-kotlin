package com.benhurqs.network.domain.usecase.vivareal

import TestVivaRealUseCaseHelper
import com.benhurqs.network.Utils.Mocks
import com.benhurqs.network.domain.model.BusinessType
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VivaRealUseCaseTest{

    lateinit var vivaRealUseCase: TestVivaRealUseCaseHelper

    @Before
    fun setUp(){
        vivaRealUseCase =
            TestVivaRealUseCaseHelper(Mocks.getListMock())
    }

    @Test
    fun `Check rental condition when all conditions is satisfied`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()

        assertTrue(vivaRealUseCase.checkRentalCondition(imovel))
    }

    @Test
    fun `Check rental condition when business type is sales`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()

        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        assertFalse(vivaRealUseCase.checkRentalCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is null`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.monthlyCondoFee = null

        assertFalse(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is not numeric`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.monthlyCondoFee = "abc"

        assertFalse(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is 0`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.monthlyCondoFee = "0"

        assertFalse(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is not 0`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 123456.0
        imovel.pricingInfos?.monthlyCondoFee = "1234"

        assertTrue(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is 30% higher value`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 123456.0
        imovel.pricingInfos?.monthlyCondoFee = (imovel.pricingInfos!!.price * 0.34).toString()

        assertFalse(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental condoFee condition when monthlyCondoFee is not 30% higher value`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 123456.0
        imovel.pricingInfos?.monthlyCondoFee = (imovel.pricingInfos!!.price * 0.29).toString()

        assertTrue(vivaRealUseCase.checkRentalCondoFeeCondition(imovel))
    }

    @Test
    fun `Check rental bounding box condition when location inside box`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 4000.0 * 1.6
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()

        assertFalse(vivaRealUseCase.checkRentalBoundingBoxCondition(imovel))
    }

    @Test
    fun `Check rental bounding box condition when location inside box and price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 4000.0 * 1.3
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()

        assertTrue(vivaRealUseCase.checkRentalBoundingBoxCondition(imovel))
    }

    @Test
    fun `Check rental bounding box condition when location out box`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 4000.0 * 1.6
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()

        assertFalse(vivaRealUseCase.checkRentalBoundingBoxCondition(imovel))
    }

    @Test
    fun `Check rental bounding box condition when location out box and price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.price = 4000.0 * 0.3
        imovel.address?.geoLocation?.location = Mocks.getLocationInsideBox()

        assertTrue(vivaRealUseCase.checkRentalBoundingBoxCondition(imovel))
    }

    @Test
    fun `Check sales condition when businness type is rental`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.RENTAL.name

        assertFalse(vivaRealUseCase.checkSalesCondition(imovel))
    }


    @Test
    fun `Check sales condition when price is lower`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        imovel.pricingInfos?.price = 500000.0

        assertTrue(vivaRealUseCase.checkSalesCondition(imovel))
    }


    @Test
    fun `Check sales condition when price is higher`(){
        val imovel = Mocks.getImovel()
        imovel.pricingInfos = Mocks.getPricingInfoRental()
        imovel.pricingInfos?.businessType = BusinessType.SALE.name
        imovel.pricingInfos?.price = 800000.0

        assertFalse(vivaRealUseCase.checkSalesCondition(imovel))
    }


}