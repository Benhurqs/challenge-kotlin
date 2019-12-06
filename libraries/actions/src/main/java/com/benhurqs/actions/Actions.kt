package com.benhurqs.actions

import android.content.Context
import android.content.Intent

object Actions {

    fun openListIntent(context: Context) = internalIntent(context, "com.benhurqs.mainlist.open")

    fun openDetailIntent(context: Context) = internalIntent(context, "com.benhurqs.detail.open")

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}