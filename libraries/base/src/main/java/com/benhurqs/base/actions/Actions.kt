package com.benhurqs.base.actions

import android.content.Context
import android.content.Intent

object Actions {

    fun openDetailIntent(context: Context) = internalIntent(context, "com.benhurqs.detail.open")

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}