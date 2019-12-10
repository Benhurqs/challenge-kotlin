package com.benhurqs.network.data

import com.benhurqs.base.model.Imovel
import io.reactivex.Observable
import retrofit2.http.GET

interface ZapAPI{

    @GET("source-1.json")
    fun list(): Observable<List<Imovel>?>


}