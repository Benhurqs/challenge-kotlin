package com.benhurqs.network.domain.repository

import com.benhurqs.network.data.APICallback
import com.benhurqs.network.data.ZapAPIService
import com.benhurqs.network.domain.model.BusinessType
import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.usecase.ListUseCase
import com.benhurqs.network.domain.usecase.VivaRealUseCase
import com.benhurqs.network.domain.usecase.ZapUseCase
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListRepository(
    val apiService: ZapAPIService = ZapAPIService(),
    val ioScheduler: Scheduler = Schedulers.io(),
    val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) {

    var zapUseCase: ListUseCase? = null
    var vivaRealUseCase: ListUseCase? = null


    companion object {
        private var mInstance: ListRepository? = null

        @Synchronized
        fun getInstance(): ListRepository {
            if (mInstance == null) {
                mInstance = ListRepository()
            }
            return mInstance!!
        }
    }

    fun callListAPI(callback: APICallback<List<Imovel>?>? = null) {
        apiService.getList()
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe {
                callback?.onStart()
            }
            .subscribe(object : Observer<List<Imovel>?> {
                override fun onError(e: Throwable?) {
                    callback?.onError()
                }

                override fun onNext(response: List<Imovel>?) {
                    if(response.isNullOrEmpty()){
                        callback?.onError()
                    }else{
                        zapUseCase = ZapUseCase(response)
                        vivaRealUseCase = VivaRealUseCase(response)

                        callback?.onSuccess(response)
                    }
                }

                override fun onComplete() {
                    callback?.onFinish()
                }

                override fun onSubscribe(d: Disposable?) {}

            })
    }


    fun getZapList() : List<Imovel>? = zapUseCase?.getList()
    fun getVivaRealList(): List<Imovel>? = vivaRealUseCase?.getList()

}