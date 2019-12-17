package com.benhurqs.mainlist.presentation.ui.activity

import android.animation.Animator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.benhurqs.base.actions.Actions
import com.benhurqs.mainlist.presentation.adapter.ListAdapter
import com.benhurqs.network.data.APICallback
import com.benhurqs.base.model.Imovel
import com.benhurqs.mainlist.R
import com.benhurqs.mainlist.presentation.contract.MainListPresenter
import com.benhurqs.mainlist.presentation.contract.MainListView
import com.benhurqs.mainlist.presentation.presenter.ListPresenter
import com.benhurqs.network.domain.repository.ListRepository
import com.benhurqs.uicomponents.animation.AnimationView
import com.benhurqs.uicomponents.ui.views.ErrorViewRender
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.loading_content.*


class ListActivity : AppCompatActivity(), MainListView{
    var presenter: MainListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        presenter = ListPresenter(this, ListRepository.getInstance())
        presenter?.callAPI()
    }

    override fun onStart() {
        super.onStart()

        filter_btn.setOnClickListener { presenter?.managerFilter() }
        filter_close_btn.setOnClickListener { hideFilter() }
        filter_viva_real.setOnClickListener { presenter?.loadVivaReal() }
        filter_zap.setOnClickListener { presenter?.loadZap() }
        (error_content as ErrorViewRender).setOnClickRetry { presenter?.callAPI() }
    }


    override fun isAdded() = !isFinishing

    override fun loadList(list: List<Imovel>) {
        list_recyclerview.layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        list_recyclerview.adapter = ListAdapter(list){
            startActivity(Actions.openDetailIntent(this, it))
        }
    }

    override fun setTitle(title: String) {
        list_title.text = title
    }

    override fun showProgress() {
        laoding_content.visibility = View.VISIBLE
        list_loading.playAnimation()
    }

    override fun hideProgress() {
        laoding_content.visibility = View.GONE
        list_loading.pauseAnimation()
    }

    override fun showError() {
        error_content.visibility = View.VISIBLE
    }

    override fun hideError() {
        error_content.visibility = View.GONE
    }

    override fun showFilter() {
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

    override fun hideFilter() {
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

    override fun hideCloseFilter() {
        filter_close_btn.visibility = View.GONE
    }

    override fun showCloseFilter() {
        filter_close_btn.visibility = View.VISIBLE
    }
}