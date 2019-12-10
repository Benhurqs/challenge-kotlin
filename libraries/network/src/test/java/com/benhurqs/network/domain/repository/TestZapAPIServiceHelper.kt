package com.benhurqs.network.domain.repository

import com.benhurqs.network.Utils.Mocks
import com.benhurqs.network.data.ZapAPIService
import com.benhurqs.base.model.Imovel
import io.reactivex.Observable

class TestZapAPIServiceHelper : ZapAPIService(){

    var mockList: List<Imovel>? = Mocks.getListMock()

    override fun getList(): Observable<List<Imovel>?> {
        return Observable.just(mockList)
    }


}