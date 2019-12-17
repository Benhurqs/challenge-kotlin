package com.benhurqs.detail.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.benhurqs.base.utils.ImageUtils
import com.benhurqs.detail.R
import kotlinx.android.synthetic.main.detail_photo_item.view.*


class PhotosAdapter(val context: Context, val imageList: Array<String>) : PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_photo_item, container , false)
        ImageUtils.loadImage(view.imovel_photo, imageList.get(position))

        container.addView(view)
        return view
    }
}