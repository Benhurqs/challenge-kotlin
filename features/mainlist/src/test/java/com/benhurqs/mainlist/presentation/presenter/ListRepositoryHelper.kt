package com.benhurqs.mainlist.presentation.presenter

import com.benhurqs.base.model.Imovel
import com.benhurqs.network.data.APICallback
import com.benhurqs.network.domain.repository.ListRepository

open class ListRepositoryHelper : ListRepository(){

    override fun getZapList(): List<Imovel>? {
        return null
    }

    override fun getVivaRealList(): List<Imovel>? {
        return null
    }

    override fun callListAPI(callback: APICallback?) {}
}