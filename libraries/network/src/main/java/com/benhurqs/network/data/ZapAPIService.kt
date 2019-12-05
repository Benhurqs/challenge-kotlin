package com.benhurqs.network.data

import com.benhurqs.network.BuildConfig
import com.benhurqs.network.domain.model.Imovel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ZapAPIService  {

    private val retrofit: Retrofit
    private val api: ZapAPI
    private val TIMEOUT: Long = 20

    init {

        val httpClient = OkHttpClient.Builder().apply {

            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG){
                val logging = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

                addInterceptor(logging).build()
            }
        }.build()


        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()

        api = retrofit.create(ZapAPI::class.java)
    }

    fun getList(): Observable<List<Imovel>?> = api.list()


}