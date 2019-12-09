package com.benhurqs.uicomponents

import androidx.annotation.RequiresApi


import android.animation.Animator
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils

/**
 * Created by benhur.souza on 15/02/18.
 */

object AnimationView{

    var CENTER_X: String = "center_x"
    var CENTER_Y: String = "center_y"

    interface AnimationViewListener{
        //        fun onAnimationRepeat(animation: Animation?)
        fun onAnimationEnd(animation: Animation?)
        fun onAnimationStart(animation: Animation?)
    }

    interface AnimatorViewListener{
        //        fun onAnimationRepeat(animation: Animation?)
        fun onAnimationEnd(animation: Animator?)
        fun onAnimationStart(animation: Animator?)
    }



    private fun animationUtil(animation: Int, slideIn: Boolean ,context: Context, view: View, listener: AnimationViewListener? = null){
        val animator = AnimationUtils.loadAnimation(context, animation)
        animator.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
//                if(listener != null){
//                    listener.onAnimationRepeat(animation)
//                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                managerViewVisibility(slideIn, view)
                if(listener != null){
                    listener.onAnimationEnd(animation)
                }

            }

            override fun onAnimationStart(animation: Animation?) {
                managerViewVisibility(!slideIn, view)
                if(listener != null){
                    listener.onAnimationStart(animation)
                }

            }
        })

        view.startAnimation(animator)
    }

    private fun managerViewVisibility(slideIn: Boolean, view: View){
        if(slideIn){
            view.visibility = View.VISIBLE
        }else {
            view.visibility = View.INVISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startRevealCircularAnimation(view: View, centerX: Int, centerY: Int, listener: AnimationView.AnimatorViewListener? = null){
        animation(view,
            centerX,
            centerY,
            0f,
            Math.max(view.width.toFloat(), view.height.toFloat()),
            listener)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun endRevealCircularAnimation(view: View, centerX: Int, centerY: Int, listener: AnimationView.AnimatorViewListener? = null){
        animation(view,
            centerX,
            centerY,
            Math.max(view.width.toFloat(), view.height.toFloat()),
            0f,
            listener)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animation(view: View, centerX: Int, centerY: Int, start: Float, end: Float, listener: AnimationView.AnimatorViewListener? = null){
        var anim = ViewAnimationUtils.createCircularReveal(view,
            centerX,
            centerY,
            0f,
            Math.max(view.width.toFloat(), view.height.toFloat()))
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
