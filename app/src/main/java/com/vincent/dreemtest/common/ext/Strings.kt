package com.vincent.dreemtest.common.ext

import android.content.Context
import com.vincent.dreemtest.R
import com.vincent.dreemtest.domain.entity.SleepStage

fun SleepStage.toString(context: Context): String = when (this) {
    SleepStage.WAKE -> R.string.sleep_stage_wake
    SleepStage.LIGHT_SLEEP -> R.string.sleep_stage_light
    SleepStage.DEEP_SLEEP -> R.string.sleep_stage_deep
    SleepStage.REM -> R.string.sleep_stage_rem
    else -> R.string.sleep_stage_unknown
}.run { context.getString(this) }