package com.benhurqs.network.domain.repository

import com.benhurqs.network.Utils.Mocks
import com.benhurqs.network.data.APICallback
import com.benhurqs.base.model.Imovel
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListRepositoryTest{

    @Mock
    private lateinit var callback: APICallback

    private lateinit var service: TestZapAPIServiceHelper

    private lateinit var repository: ListRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        val testScheduler = Schedulers.trampoline()
        service = TestZapAPIServiceHelper()
        repository = ListRepository( service, testScheduler, testScheduler)
    }

    @Test
    fun `Check if call callbacks when list is success`(){
        repository.callListAPI(callback)
        Mockito.verify(callback, Mockito.times(1)).onStart()
        Mockito.verify(callback, Mockito.times(1)).onSuccess()
        Mockito.verify(callback, Mockito.times(1)).onFinish()
        Mockito.verify(callback, Mockito.never()).onError(Mockito.anyString())
    }


    @Test
    fun `Check if call onError when list is empty`(){
        service.mockList = ArrayList<Imovel>()

        repository.callListAPI(callback)
        Mockito.verify(callback, Mockito.times(1)).onStart()
        Mockito.verify(callback, Mockito.never()).onSuccess()
        Mockito.verify(callback, Mockito.times(1)).onError(Mockito.anyString())
        Mockito.verify(callback, Mockito.times(1)).onFinish()

        Assert.assertTrue(repository.getVivaRealList().isNullOrEmpty())
        Assert.assertTrue(repository.getZapList().isNullOrEmpty())
    }

    @Test
    fun `Check if filter Zap list`(){
        service.mockList = ArrayList<Imovel>().apply {
            add(Mocks.getRentalZapImovel())
            add(Mocks.getSalesZapImovel())
        }

        repository.callListAPI(callback)

        Mockito.verify(callback, Mockito.times(1)).onStart()
        Mockito.verify(callback, Mockito.times(1)).onSuccess()
        Mockito.verify(callback, Mockito.times(1)).onFinish()
        Mockito.verify(callback, Mockito.never()).onError(Mockito.anyString())

        Assert.assertTrue(service.mockList!!.size == 2)
        Assert.assertTrue(repository.getVivaRealList().isNullOrEmpty())
        Assert.assertFalse(repository.getZapList().isNullOrEmpty())
        Assert.assertTrue(repository.getZapList()!!.size == 2)
    }

    @Test
    fun `Check if filter Viva Real list`(){
        service.mockList = ArrayList<Imovel>().apply {
            add(Mocks.getRentalVivaRealImovel())
            add(Mocks.getSalesVivaImovel())
        }

        repository.callListAPI(callback)

        Mockito.verify(callback, Mockito.times(1)).onStart()
        Mockito.verify(callback, Mockito.times(1)).onSuccess()
        Mockito.verify(callback, Mockito.times(1)).onFinish()
        Mockito.verify(callback, Mockito.never()).onError(Mockito.anyString())

        Assert.assertTrue(service.mockList!!.size == 2)
        Assert.assertTrue(repository.getZapList().isNullOrEmpty())
        Assert.assertFalse(repository.getVivaRealList().isNullOrEmpty())
        Assert.assertTrue(repository.getVivaRealList()!!.size == 2)
    }

    @Test
    fun `Check if filter Viva Real and Zap list`(){

        service.mockList = ArrayList<Imovel>().apply {
            add(Mocks.getRentalVivaRealImovel())
            add(Mocks.getSalesVivaImovel())
            add(Mocks.getRentalZapImovel())
        }

        repository.callListAPI(callback)

        Mockito.verify(callback, Mockito.times(1)).onStart()
        Mockito.verify(callback, Mockito.times(1)).onSuccess()
        Mockito.verify(callback, Mockito.times(1)).onFinish()
        Mockito.verify(callback, Mockito.never()).onError(Mockito.anyString())

        Assert.assertTrue(service.mockList!!.size == 3)
        Assert.assertFalse(repository.getZapList().isNullOrEmpty())
        Assert.assertFalse(repository.getVivaRealList().isNullOrEmpty())
        Assert.assertTrue(repository.getVivaRealList()!!.size == 2)
        Assert.assertTrue(repository.getZapList()!!.size == 1)
        Assert.assertTrue(repository.getZapList()!!.get(0).id == Mocks.getRentalZapImovel().id)
    }


}