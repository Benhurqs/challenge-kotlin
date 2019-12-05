package com.benhurqs.zapchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.benhurqs.network.data.APICallback
import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.repository.ListRepository

class MainActivity : AppCompatActivity() {

    private var repository = ListRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       repository.callListAPI(object : APICallback<List<Imovel>?>{
            override fun onStart() {

            }

            override fun onError() {
            }

            override fun onFinish() {
            }

            override fun onSuccess(response: List<Imovel>?) {
                var viva = repository.getVivaRealList()
                var zap = repository.getZapList()

                Log.e("Viva", viva?.size.toString())
                Log.e("Zap", zap?.size.toString())

                Log.e("Viva - 1", viva?.get(0)?.id)
                Log.e("Zap - 1", zap?.get(0)?.id)
            }
        })
    }
}
