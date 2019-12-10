package com.benhurqs.mainlist.presentation.contract

import com.benhurqs.base.model.Imovel

interface MainListView {

    fun isAdded(): Boolean
    fun loadList(list: List<Imovel>)
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun hideError()
    fun showFilter()
    fun hideFilter()
    fun setTitle(title: String)
    fun hideCloseFilter()
    fun showCloseFilter()
}