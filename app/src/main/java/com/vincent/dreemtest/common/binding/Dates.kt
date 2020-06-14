package com.vincent.dreemtest.common.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("date", "format")
fun setDate(textView: TextView, date: ZonedDateTime?, format: String) {
    textView.text = date?.run { format(DateTimeFormatter.ofPattern(format)) }
}

@BindingAdapter("time", "format")
fun setTime(textView: TextView, time: LocalTime?, format: String) {
    textView.text = time?.run { format(DateTimeFormatter.ofPattern(format)) }
}