package com.benhurqs.uicomponents.animation


import android.animation.Animator
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi

/**
 * Created by benhur.souza on 15/02/18.
 */

object AnimationView{

    var CENTER_X: String = "center_x"
    var CENTER_Y: String = "center_y"

    interface AnimatorViewListener{
        fun onAnimationEnd(animation: Animator?)
        fun onAnimationStart(animation: Animator?)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startRevealCircularAnimation(view: View, centerX: Int, centerY: Int, listener: AnimatorViewListener? = null){
        animation(
            view,
            centerX,
            centerY,
            0f,
            Math.max(view.width.toFloat(), view.height.toFloat()),
            listener
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun endRevealCircularAnimation(view: View, centerX: Int, centerY: Int, listener: AnimatorViewListener? = null){
        animation(
            view,
            centerX,
            centerY,
            Math.max(view.width.toFloat(), view.height.toFloat()),
            0f,
            listener
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animation(view: View, centerX: Int, centerY: Int, start: Float, end: Float, listener: AnimatorViewListener? = null){
        var anim = ViewAnimationUtils.createCircularReveal(view,
            centerX,
            centerY,
            start,
            end)

        anim.duration = 500
        anim.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if(listener != null){
                    listener.onAnimationEnd(animation)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                if(listener != null){
                    listener.onAnimationStart(animation)
                }
            }
        })
        anim.start()
    }



}
