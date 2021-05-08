package com.saglamceren.mybudget.extension

import android.animation.Animator
import android.util.Log
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.doOnAnimationEnd(onAnimationEnd: () -> Unit) {
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            Log.i("test", "onAnimationStart")
        }

        override fun onAnimationEnd(animation: Animator?) {
            onAnimationEnd()
        }

        override fun onAnimationCancel(animation: Animator?) {
            Log.i("test", "onAnimationCancel")
        }

        override fun onAnimationRepeat(animation: Animator?) {
            Log.i("test", "onAnimationRepeat")
        }

    })
}