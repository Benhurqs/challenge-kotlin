package com.benhurqs.detail.presentation.contract

import com.benhurqs.base.model.Imovel

interface DetailView {
    fun loadImages(list: List<String>)
    fun loadBedroom(qtd: Int)
    fun loadBathroom(qtd: Int)
    fun loadParking(qtd: Int)
    fun loadDescription(area: Int, bedroom: Int, bathroom: Int, parking: Int, monthly: String, iptu: Float)
    fun loadPrice(imovel: Imovel)
}