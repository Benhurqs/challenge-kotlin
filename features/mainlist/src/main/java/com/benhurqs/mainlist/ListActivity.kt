package com.benhurqs.mainlist

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.benhurqs.mainlist.presentation.adapter.ListAdapter
import com.benhurqs.network.data.APICallback
import com.benhurqs.base.model.Imovel
import com.benhurqs.network.domain.repository.ListRepository
import com.benhurqs.uicomponents.animation.AnimationView
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.loading_content.*


class ListActivity : AppCompatActivity(){

    private var repository = ListRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        callAPI()
    }

    private fun callAPI(){
        repository.callListAPI(object : APICallback {
            override fun onStart() {
                filter.visibility = View.VISIBLE
                laoding_content.visibility = View.VISIBLE
                list_loading.playAnimation()
            }

            override fun onError(error: String?) {
            }

            override fun onFinish() {}

            override fun onSuccess() {
                laoding_content.visibility = View.GONE
                list_loading.pauseAnimation()
            }
        })
    }

    private fun iniList(list: List<Imovel>?){
        list_recyclerview.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        list_recyclerview.adapter = ListAdapter(list!!)
        closeFilter(null)
    }

    fun loadVivaList(view: View?){
        iniList(repository.getVivaRealList())
        list_title.text = "Viva Real"
    }

    fun loadZapList(view: View?){
        iniList(repository.getZapList())
        list_title.text = "Zap"

    }

    fun openFilter(view: View?){
        filter_close_btn.playAnimation()

        filter.post(object : Runnable {
            override fun run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AnimationView.startRevealCircularAnimation(filter, filter_btn.x.toInt(), filter_btn.y.toInt(), object :
                        AnimationView.AnimatorViewListener{
                        override fun onAnimationEnd(animation: Animator?) {

                        }

                        override fun onAnimationStart(animation: Animator?) {
                            filter.visibility = View.VISIBLE
                        }
                    })
                }

            }
        })

    }

    fun closeFilter(view: View?){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimationView.endRevealCircularAnimation(filter, filter_btn.x.toInt(), filter_btn.y.toInt(), object :
                AnimationView.AnimatorViewListener{
                override fun onAnimationEnd(animation: Animator?) {
                    filter.visibility = View.GONE
                }

                override fun onAnimationStart(animation: Animator?) {

                }
            })
        }
    }

}