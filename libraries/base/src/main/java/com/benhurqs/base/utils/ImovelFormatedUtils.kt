package com.benhurqs.base.utils

import android.content.Context
import com.benhurqs.base.R
import com.benhurqs.base.model.BusinessType
import com.benhurqs.base.model.Imovel
import java.text.NumberFormat
import java.util.*

object ImovelFormatedUtils {

    fun getDescription(context: Context, imovel: Imovel): String{
        return context.getString(R.string.description, imovel.bedrooms, imovel.bathrooms, imovel.parkingSpaces, imovel.usableAreas.toString())
    }

    fun getBusinessType(context: Context, imovel: Imovel): String{
        return if(imovel.pricingInfos?.businessType == BusinessType.RENTAL.name){
            context.getString(R.string.rental)
        }else{
            context.getString(R.string.sales)
        }
    }

    fun formatPrice(context: Context, imovel: Imovel): String {
        var formattedSTR = formatValue(imovel.pricingInfos?.price)
        if(imovel.pricingInfos?.businessType == BusinessType.RENTAL.name){
            formattedSTR = context.getString(R.string.rental_price, formattedSTR)
        }

        return formattedSTR
    }

    fun formatValue(price: Float?): String{
        if (price == null) return ""
        val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        nf.maximumFractionDigits = 2

        var symbol: String? = nf.currency?.symbol
        var formattedSTR = nf.format(price)
        if (!symbol.isNullOrEmpty() && !symbol.contains(" ")) {
            formattedSTR = formattedSTR.replace(symbol, "$symbol ")
        }

        return formattedSTR

    }

    fun formatValue(price: String?): String{
        if(price == null || !price.matches("-?\\d+(\\.\\d+)?".toRegex())){
            return ""
        }

        return formatValue(price.toFloat())
    }

}