package com.benhurqs.detail.presentation.presenter

import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.PricingInfo
import com.benhurqs.detail.presentation.contract.DetailView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest{

    @Mock
    lateinit var view: DetailView

    lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view)
    }

    @Test
    fun `check if do nothing if IMOVEL is null`(){
        var list = ArrayList<String>()

        presenter.managerData(null)

        Mockito.verify(view, Mockito.never()).loadParking(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadBedroom(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadBathroom(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadDescription(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyFloat())
        Mockito.verify(view, Mockito.never()).loadImages(list)
    }

    @Test
    fun `check do nothing if pricingIfo is null`(){
        var list = ArrayList<String>()

        presenter.managerData(Imovel().apply {
            pricingInfos = null
        })

        Mockito.verify(view, Mockito.never()).loadParking(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadBedroom(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadBathroom(Mockito.anyInt())
        Mockito.verify(view, Mockito.never()).loadDescription(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyFloat())
        Mockito.verify(view, Mockito.never()).loadImages(list)

    }

    @Test
    fun `check if load data if pricingIfo is not null`(){
        val list = ArrayList<String>()
        val bathroomMock = 2
        val bedroomMock = 1
        val parkingMock = 4
        val areaMock = 45f
        val monthlyMock = "450"
        val iptuMock = 650f

        val mock = Imovel().apply {
            this.usableAreas = areaMock
            this.bathrooms = bathroomMock
            this.bedrooms = bedroomMock
            this.parkingSpaces = parkingMock
            this.images = list

            pricingInfos = PricingInfo().apply {
                this.monthlyCondoFee = monthlyMock
                this.yearlyIptu = iptuMock
            }
        }

        presenter.managerData(mock)

        Mockito.verify(view, Mockito.times(1)).loadParking(parkingMock)
        Mockito.verify(view, Mockito.times(1)).loadBathroom(bathroomMock)
        Mockito.verify(view, Mockito.times(1)).loadBedroom(bedroomMock)
        Mockito.verify(view, Mockito.times(1)).loadDescription(areaMock.toInt(), bedroomMock, bathroomMock, parkingMock, monthlyMock, iptuMock)
        Mockito.verify(view, Mockito.times(1)).loadPrice(mock)
        Mockito.verify(view, Mockito.never()).loadImages(list)
    }

    @Test
    fun `check if load images if list is not null`(){
        val list = ArrayList<String>().apply {
            add("image 1")
        }

        val bathroomMock = 2
        val bedroomMock = 1
        val parkingMock = 4
        val areaMock = 45f
        val monthlyMock = "450"
        val iptuMock = 650f

        var mock = Imovel().apply {
            this.usableAreas = areaMock
            this.bathrooms = bathroomMock
            this.bedrooms = bedroomMock
            this.parkingSpaces = parkingMock
            this.images = list

            pricingInfos = PricingInfo().apply {
                this.monthlyCondoFee = monthlyMock
                this.yearlyIptu = iptuMock
            }
        }

        presenter.managerData(mock)

        Mockito.verify(view, Mockito.times(1)).loadParking(parkingMock)
        Mockito.verify(view, Mockito.times(1)).loadBathroom(bathroomMock)
        Mockito.verify(view, Mockito.times(1)).loadBedroom(bedroomMock)
        Mockito.verify(view, Mockito.times(1)).loadDescription(areaMock.toInt(), bedroomMock, bathroomMock, parkingMock, monthlyMock, iptuMock)
        Mockito.verify(view, Mockito.times(1)).loadPrice(mock)
        Mockito.verify(view, Mockito.times(1)).loadImages(list)
    }


}