package com.benhurqs.mainlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.benhurqs.mainlist.presentation.adapter.ListAdapter
import com.benhurqs.network.data.APICallback
import com.benhurqs.network.domain.model.Imovel
import com.benhurqs.network.domain.repository.ListRepository
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.loading_content.*

class ListActivity : AppCompatActivity(){

    private var repository = ListRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
    }

    override fun onStart() {
        super.onStart()

        repository.callListAPI(object : APICallback {
            override fun onStart() {
                laoding_content.visibility = View.VISIBLE
                list_loading.playAnimation()
            }

            override fun onError(error: String?) {
            }

            override fun onFinish() {
                laoding_content.visibility = View.GONE
                list_loading.pauseAnimation()
            }

            override fun onSuccess() {
                var viva = repository.getVivaRealList()
                var zap = repository.getZapList()

                Log.e("Viva", viva?.size.toString())
                Log.e("Zap", zap?.size.toString())

                Log.e("Viva - 1", viva?.get(0)?.id)
                Log.e("Zap - 1", zap?.get(0)?.id)

                iniList(viva)
            }
        })
    }

    private fun iniList(list: List<Imovel>?){
        list_recyclerview.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        list_recyclerview.adapter = ListAdapter(list!!)
    }
}