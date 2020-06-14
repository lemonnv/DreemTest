package com.vincent.dreemtest.common.ext

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.vincent.dreemtest.R
import com.vincent.dreemtest.domain.entity.SleepStage

@ColorInt
fun SleepStage.toColor(context: Context): Int = when (this) {
    SleepStage.WAKE -> R.color.sleep_stage_wake
    SleepStage.LIGHT_SLEEP -> R.color.sleep_stage_light
    SleepStage.DEEP_SLEEP -> R.color.sleep_stage_deep
    SleepStage.REM -> R.color.sleep_stage_rem
    SleepStage.UNKNOWN -> android.R.color.transparent
}.run { ContextCompat.getColor(context, this) }