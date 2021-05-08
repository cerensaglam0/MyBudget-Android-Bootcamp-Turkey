package com.saglamceren.mybudget.extension

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

@BindingAdapter("progressFromDouble")
fun ProgressBar.progressFromDouble(progress: Double?) {
    setProgress(progress?.roundToInt() ?: 0)
}

@BindingAdapter("maxFromDouble")
fun ProgressBar.maxFromDouble(max: Double?) {
    setMax(max?.roundToInt() ?: 0)
}