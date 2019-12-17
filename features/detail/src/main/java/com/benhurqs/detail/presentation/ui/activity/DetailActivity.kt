package com.benhurqs.detail.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benhurqs.base.actions.Actions
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.utils.ImovelFormatedUtils
import com.benhurqs.detail.R
import com.benhurqs.detail.presentation.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    var imovel: Imovel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_back.setOnClickListener { this.finish() }
       imovel = intent.extras?.getSerializable(Actions.IMOVEL) as Imovel
        init()

        detail_view_pager.adapter = PhotosAdapter(this, imovel?.images!!)
        view_pager_indicator.setViewPager(detail_view_pager)
    }

    private fun init(){
        detail_bathroom_qtd.text = String.format("%02d", imovel?.bathrooms)
        detail_bed_qtd.text = String.format("%02d", imovel?.bedrooms)
        detail_parking_qtd.text = String.format("%02d", imovel?.parkingSpaces)

        detail_description.text = getString(
            R.string.detail_description,
            imovel?.usableAreas?.toInt(),
            imovel?.bedrooms,
            imovel?.bathrooms,
            imovel?.parkingSpaces,
            ImovelFormatedUtils.formatValue(imovel?.pricingInfos?.monthlyCondoFee),
            ImovelFormatedUtils.formatValue(imovel?.pricingInfos?.yearlyIptu))

        detail_price.text = ImovelFormatedUtils.formatPrice(this, imovel!!)
    }
}
