package com.benhurqs.network.domain.usecase.usacase

import com.benhurqs.network.Utils.Mocks.getAddress
import com.benhurqs.network.Utils.Mocks.getListMock
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.address.Address
import com.benhurqs.base.model.address.Geolocation
import com.benhurqs.base.model.address.Location
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ListUseCaseTest{

    @Before
    fun setUp(){}

    @Test
    fun `Check if address != null return list`(){
        var list = getListMock()
        var testUseCase =
            TestUseCaseHelper(list)
        assertFalse(testUseCase.getList().isNullOrEmpty())
        assertTrue(list == testUseCase.getList())
    }

    @Test
    fun `Check if address == null not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = null
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if gelolocation == null not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = null
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if location == null not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = Geolocation().apply {
                        location = null
                    }
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if lat == 0 not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = Geolocation().apply {
                        location = Location().apply {
                            lat = 0.0
                            lon = 12.0
                        }
                    }
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if lon == 0 not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = Geolocation().apply {
                        location = Location().apply {
                            lat = -20.0
                            lon = 0.0
                        }
                    }
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if lat == 0 and lon == 0 not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = Geolocation().apply {
                        location = Location().apply {
                            lat = 0.0
                            lon = 0.0
                        }
                    }
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if lat == -0 and lon == -0 not return list`(){
        var list: ArrayList<Imovel> = ArrayList<Imovel>()
        list.add(
            Imovel().apply {
                address = getAddress()
                address = Address().apply {
                    geoLocation = Geolocation().apply {
                        location = Location().apply {
                            lat = -0.0
                            lon = -0.0
                        }
                    }
                }
            }
        )

        var testUseCase =
            TestUseCaseHelper(list)
        assertTrue(testUseCase.getList().isNullOrEmpty())
        assertFalse(list == testUseCase.getList())
    }

    @Test
    fun `Check if insideBoundingBox == false when lat = 0 `(){

        var testUseCase =
            TestUseCaseHelper(getListMock())
        val location = Location().apply {
            lat = 0.0
            lon =  -46.641146

        }

        assertFalse(testUseCase.checkBoundingBox(location))
    }


    @Test
    fun `Check if insideBoundingBox == false when lon = 0 `(){

        var testUseCase =
            TestUseCaseHelper(getListMock())
        val location = Location().apply {
            lat = -23.568704
            lon =  0.0

        }

        assertFalse(testUseCase.checkBoundingBox(location))
    }

    @Test
    fun `Check if insideBoundingBox == true when the location is on the max border`(){

        var testUseCase =
            TestUseCaseHelper(getListMock())
        val location = Location().apply {
            lat = -23.546686
            lon = -46.641146
        }

        assertTrue(testUseCase.checkBoundingBox(location))
    }

    @Test
    fun `Check if insideBoundingBox == true when the location is on the min border`(){

        var testUseCase =
            TestUseCaseHelper(getListMock())
        val location = Location().apply {
            lat = -23.568704
            lon = -46.693419

        }

        assertTrue(testUseCase.checkBoundingBox(location))
    }

    @Test
    fun `Check if insideBoundingBox == true when the location is inside box`(){

        var testUseCase =
            TestUseCaseHelper(getListMock())
        val location = Location().apply {
            lat = -23.558704
            lon = -46.673419

        }

        assertTrue(testUseCase.checkBoundingBox(location))
    }




}
