package com.benhurqs.detail.presentation.presenter

import com.benhurqs.base.model.Imovel
import com.benhurqs.detail.presentation.contract.DetailPresenterContract
import com.benhurqs.detail.presentation.contract.DetailView

class DetailPresenter (val view: DetailView): DetailPresenterContract{

    private var imovel: Imovel? = null
    override fun managerData(imovel: Imovel?) {
        this.imovel = Imovel()

        if(imovel?.pricingInfos != null){
            view.loadBathroom(imovel.bathrooms)
            view.loadBedroom(imovel.bedrooms)
            view.loadParking(imovel.parkingSpaces)
            view.loadDescription(imovel.usableAreas.toInt(),
                imovel.bedrooms,
                imovel.bathrooms,
                imovel.parkingSpaces,
                imovel.pricingInfos?.monthlyCondoFee!!,
                imovel.pricingInfos?.yearlyIptu!!)

            view.loadPrice()

            if(!imovel.images.isNullOrEmpty()){
                view.loadImages(imovel.images!!)
            }
        }
    }
}