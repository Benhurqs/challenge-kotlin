package com.benhurqs.mainlist.presentation.presenter

import com.benhurqs.base.model.Imovel
import com.benhurqs.mainlist.presentation.contract.MainListView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ListPresenterTest{

    @Mock
    private lateinit var view: MainListView

    @Mock
    private lateinit var repository: ListRepositoryHelper

    private lateinit var presenter: ListPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = ListPresenter(view, repository)
    }

    @Test
    fun `check if callAPI is called`(){
        presenter.callAPI()
        Mockito.verify(repository, Mockito.times(1)).callListAPI(presenter)
    }

    @Test
    fun `check if show error when list is null`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.loadVivaReal()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).showError()
        Mockito.verify(view, Mockito.never()).setTitle("Viva Real")
        Mockito.verify(view, Mockito.never()).hideFilter()

    }

    @Test
    fun `check if show error when list is empty`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.loadVivaReal()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).showError()
        Mockito.verify(view, Mockito.never()).setTitle("Viva Real")
        Mockito.verify(view, Mockito.never()).hideFilter()

    }

    @Test
    fun `check if load Viva Real list when list is not null or empty`(){


        Mockito.`when`(view.isAdded()).thenReturn(true)
        Mockito.`when`(repository.getVivaRealList()).thenReturn(getListMock())

        presenter.loadVivaReal()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).setTitle("Viva Real")
        Mockito.verify(view, Mockito.times(1)).hideFilter()
        Mockito.verify(repository, Mockito.times(1)).getVivaRealList()
        Mockito.verify(view, Mockito.never()).showError()
    }

    @Test
    fun `check if load Zap list when list is not null or empty`(){
       Mockito.`when`(view.isAdded()).thenReturn(true)
        Mockito.`when`(repository.getZapList()).thenReturn(getListMock())

        presenter.loadZap()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).setTitle("Zap")
        Mockito.verify(view, Mockito.times(1)).hideFilter()
        Mockito.verify(repository, Mockito.times(1)).getZapList()
        Mockito.verify(view, Mockito.never()).showError()
    }

    @Test
    fun `check if show progress when start is called`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.onStart()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).showProgress()
        Mockito.verify(view, Mockito.times(1)).hideError()
    }


    @Test
    fun `check if do nothing when start api and isAdded is false`(){
        Mockito.`when`(view.isAdded()).thenReturn(false)

        presenter.onStart()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.never()).showProgress()
        Mockito.verify(view, Mockito.never()).hideError()
    }

    @Test
    fun `check if show error when onError is called`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.onError("Error")

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).hideProgress()
        Mockito.verify(view, Mockito.times(1)).showError()
    }


    @Test
    fun `check if do nothing when onError is called and isAdded is false`(){
        Mockito.`when`(view.isAdded()).thenReturn(false)

        presenter.onError("Error")

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.never()).hideProgress()
        Mockito.verify(view, Mockito.never()).showError()
    }

    @Test
    fun `check if hide progress when onFinish is called`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.onFinish()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).hideProgress()
    }


    @Test
    fun `check if do nothing when onFinish is called and isAdded is false`(){
        Mockito.`when`(view.isAdded()).thenReturn(false)

        presenter.onFinish()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.never()).hideProgress()
    }

    @Test
    fun `check if show filter when managerFilter is called`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)

        presenter.managerFilter()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).showCloseFilter()
        Mockito.verify(view, Mockito.times(1)).showFilter()
    }


    @Test
    fun `check if do nothing when managerFilter is called and isAdded is false`(){
        Mockito.`when`(view.isAdded()).thenReturn(false)

        presenter.managerFilter()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.never()).showCloseFilter()
        Mockito.verify(view, Mockito.never()).showFilter()
    }

    @Test
    fun `check if show filter when onSuccess is called and hasBrandSelected is false`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)
        presenter.hasBrandSelected = false

        presenter.onSuccess()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).hideProgress()
        Mockito.verify(view, Mockito.times(1)).showFilter()
        Mockito.verify(view, Mockito.times(1)).hideCloseFilter()
    }

    @Test
    fun `check if not show filter when onSuccess is called and hasBrandSelected is true`(){
        Mockito.`when`(view.isAdded()).thenReturn(true)
        presenter.hasBrandSelected = true

        presenter.onSuccess()

        Mockito.verify(view, Mockito.times(1)).isAdded()
        Mockito.verify(view, Mockito.times(1)).hideProgress()
        Mockito.verify(view, Mockito.never()).showFilter()
        Mockito.verify(view, Mockito.never()).hideCloseFilter()
    }




    private fun getListMock() =  ArrayList<Imovel>().apply {
        add(Imovel())
    }

}