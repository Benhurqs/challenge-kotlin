package com.benhurqs.uicomponents.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.benhurqs.uicomponents.R
import kotlinx.android.synthetic.main.error_content.view.*

class ErrorViewRender(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    init {
        val view = View.inflate(context, R.layout.error_content, null)
        addView(view)
    }

    fun setOnClickRetry(listener: () -> Unit){
        rootView.error_retry.setOnClickListener {
            listener()
        }
    }

}