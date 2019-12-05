package com.benhurqs.network.repository

import com.benhurqs.network.api.APICallback
import com.benhurqs.network.api.ZapAPIService
import com.benhurqs.network.domain.Imovel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListRepository(
    val apiService: ZapAPIService = ZapAPIService(),
    val ioScheduler: Scheduler = Schedulers.io(),
    val mainScheduler: Scheduler = AndroidSchedulers.mainThread()) {

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

    private fun callRemoteAPI(albumID: Int, callback: APICallback<List<Imovel>?>? = null) {
        apiService.getList()
            .map { it?.filter { imovel-> imovel.owner } }
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe {
                callback?.onStart()
            }
            .subscribe(object : Observer<List<Imovel>?> {
                override fun onError(e: Throwable?) {
                    callback?.onError()
                }

                override fun onNext(value: List<Imovel>?) {
                    callback?.onSuccess(value)
                }

                override fun onComplete() {
                    callback?.onFinish()
                }

                override fun onSubscribe(d: Disposable?) {}

            })
    }


}