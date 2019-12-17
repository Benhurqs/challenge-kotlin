package com.benhurqs.detail.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.base.actions.Actions
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.utils.ImovelFormatedUtils
import com.benhurqs.detail.R
import com.benhurqs.detail.presentation.adapter.PhotosAdapter
import com.benhurqs.detail.presentation.contract.DetailPresenterContract
import com.benhurqs.detail.presentation.contract.DetailView
import com.benhurqs.detail.presentation.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    var imovel: Imovel? = null
    var presenter: DetailPresenterContract? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onStart() {
        super.onStart()

        imovel = intent.extras?.getSerializable(Actions.IMOVEL) as Imovel
        detail_back.setOnClickListener { this.finish() }
        presenter = DetailPresenter(this)
        presenter?.managerData(imovel)
    }

    override fun loadImages(list: List<String>) {
        detail_view_pager.adapter = PhotosAdapter(this, list)
        view_pager_indicator.setViewPager(detail_view_pager)
    }

    override fun loadBedroom(qtd: Int) {
        detail_bed_qtd.text = String.format("%02d", qtd)
    }

    override fun loadBathroom(qtd: Int) {
        detail_bathroom_qtd.text = String.format("%02d", qtd)
    }

    override fun loadParking(qtd: Int) {
        detail_parking_qtd.text = String.format("%02d", qtd)
    }

    override fun loadDescription(
        area: Int,
        bedroom: Int,
        bathroom: Int,
        parking: Int,
        monthly: String,
        iptu: Float
    ) {
        detail_description.text = getString(
            R.string.detail_description,
            area,
            bedroom,
            bathroom,
            parking,
            ImovelFormatedUtils.formatValue(monthly),
            ImovelFormatedUtils.formatValue(iptu))
    }

    override fun loadPrice() {
        detail_price.text = ImovelFormatedUtils.formatPrice(this, imovel!!)
    }
}
