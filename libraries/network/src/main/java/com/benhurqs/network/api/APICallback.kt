package com.benhurqs.network.api

interface APICallback<RESPONSE> {
    fun onStart()
    fun onError()
    fun onFinish()
    fun onSuccess(response: RESPONSE)
}