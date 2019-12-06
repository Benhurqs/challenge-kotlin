package com.benhurqs.network.data

interface APICallback {
    fun onStart()
    fun onError(error: String?)
    fun onFinish()
    fun onSuccess()
}