package com.benhurqs.base.actions

import android.content.Context
import android.content.Intent
import com.benhurqs.base.model.Imovel

object Actions {
    const val IMOVEL = "imovel"

    fun openDetailIntent(context: Context, imovel: Imovel) = internalIntent(context, "com.benhurqs.detail.open").putExtra(
        IMOVEL, imovel )

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}