package com.benhurqs.network.api

import com.benhurqs.network.domain.Imovel
import io.reactivex.Observable
import retrofit2.http.GET

interface ZapAPI{

    @GET("")
    fun list(): Observable<List<Imovel>?>


}