package com.benhurqs.detail.presentation.contract

import com.benhurqs.base.model.Imovel

interface DetailPresenterContract {
    fun managerData(imovel: Imovel?)
}