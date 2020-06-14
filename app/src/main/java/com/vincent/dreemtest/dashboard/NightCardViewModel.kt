package com.vincent.dreemtest.dashboard

import android.content.Context
import androidx.core.graphics.scaleMatrix
import com.vincent.dreemtest.R
import com.vincent.dreemtest.domain.entity.Night
import org.koin.java.KoinJavaComponent.inject
import java.time.Duration
import java.time.LocalTime
import java.time.Period
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

data class NightCardViewModel(val id: String, val date: ZonedDateTime, val sleepDuration: LocalTime, val sleepOnsetDuration: LocalTime, val quality: Float)

internal fun Night.toCardViewModel(): NightCardViewModel {
    return NightCardViewModel(
        id = id,
        date = recordDateRange.endInclusive,
        sleepDuration = recordDateRange.endInclusive.timeSince(recordDateRange.start),
        sleepOnsetDuration = sleepOnsetDuration.toLocalTime(),
        quality = sleepScore
    )
}

private fun ZonedDateTime.timeSince(other: ZonedDateTime): LocalTime = Duration.between(this, other).toLocalTime()

private fun Duration.toLocalTime(): LocalTime = LocalTime.of(toHours().toInt(), toMinutes().toInt(), seconds.toInt())