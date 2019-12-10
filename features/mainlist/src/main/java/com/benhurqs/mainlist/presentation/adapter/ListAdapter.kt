package com.benhurqs.mainlist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benhurqs.mainlist.R
import com.benhurqs.base.model.BusinessType
import com.benhurqs.base.model.Imovel
import com.benhurqs.base.model.PricingInfo
import com.benhurqs.base.utils.ImovelFormatedUtils
import com.benhurqs.uicomponents.adapter.DefaultViewHolder
import kotlinx.android.synthetic.main.item_content.view.*
import java.text.NumberFormat
import java.util.*

class ListAdapter(val list: List<Imovel>)  : RecyclerView.Adapter<DefaultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_content,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val view: View = holder.mView
        val item = list[position]

        if(!item.images.isNullOrEmpty()){
            if(item.images!!.size > 3){
                view.item_images_content.visibility = View.VISIBLE
                com.benhurqs.base.utils.ImageUtils.loadImage(view.item_image_2, item.images!![1])
                com.benhurqs.base.utils.ImageUtils.loadImage(view.item_image_3, item.images!![2])
            }else{
                view.item_images_content.visibility = View.GONE
            }

            com.benhurqs.base.utils.ImageUtils.loadImage(view.item_image_1, item.images!![0])

            view.item_description.text = ImovelFormatedUtils.getDescription(view.context, item)
            view.price.text = ImovelFormatedUtils.formatPrice(view.context, item)
            view.type.text = ImovelFormatedUtils.getBussinessType(view.context, item)
        }
    }



}