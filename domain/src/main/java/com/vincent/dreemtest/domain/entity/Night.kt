package com.vincent.dreemtest.domain.entity

import java.io.Serializable
import java.time.Duration
import java.time.ZonedDateTime

data class Night(
    val id: String,
    val recordDateRange: ClosedRange<ZonedDateTime>,
    val sleepDateRange: ClosedRange<ZonedDateTime>,
    val sleepOnsetDuration: Duration,
    val sleepScore: Float,
    val numberOfStimulation: Int,
    val numberOfMove: Int,
    val heartRateAverage: Float,
    val hypnogram: List<SleepStageValue>
): Serializable