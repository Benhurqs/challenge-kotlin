package com.benhurqs.mainlist.presentation.presenter

import com.benhurqs.base.model.Imovel
import com.benhurqs.mainlist.presentation.contract.MainListPresenter
import com.benhurqs.mainlist.presentation.contract.MainListView
import com.benhurqs.network.data.APICallback
import com.benhurqs.network.domain.repository.ListRepository

class ListPresenter(val view: MainListView) : MainListPresenter, APICallback{

    var hasBrandSelected = false
    var repository = ListRepository.getInstance()

    override fun callAPI() {
        repository.callListAPI(this)
    }

    override fun loadZap() {
        loadList(repository.getZapList(), "Zap")
    }

    override fun loadVivaReal() {
        loadList(repository.getVivaRealList(), "Viva Real")

    }

    private fun loadList(list: List<Imovel>?, title: String){
        if(view.isAdded()){
            if(list.isNullOrEmpty()){
                view.showError()
            }else{
                hasBrandSelected = true
                view.loadList(list)
                view.setTitle(title)
                view.hideFilter()
            }
        }
    }

    override fun onStart() {
        if(view.isAdded()){
            view.showProgress()
        }
    }

    override fun onError(error: String?) {
        if(view.isAdded()){
            view.hideProgress()
            view.showError()
        }
    }

    override fun onFinish() {
        if(view.isAdded()){
            view.hideProgress()
        }
    }

    override fun onSuccess() {
        if(view.isAdded()){
            view.hideProgress()

            if(!hasBrandSelected){
                view.showFilter()
                view.hideCloseFilter()
            }
        }
    }

    override fun managerFilter() {
        if(view.isAdded()){
            view.showCloseFilter()
            view.showFilter()
        }
    }
}