package com.vincent.dreemtest.common.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

@BindingAdapter("percentage")
fun setPercentage(textView: TextView, percentage: Float?) {
    textView.text = percentage?.let { (it * 100f).roundToInt().toString() + "%" }
}