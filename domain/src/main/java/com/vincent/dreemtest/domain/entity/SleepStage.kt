package com.vincent.dreemtest.domain.entity

import java.io.Serializable
import java.time.ZonedDateTime

enum class SleepStage: Serializable {
    UNKNOWN, WAKE, LIGHT_SLEEP, DEEP_SLEEP, REM
}

data class SleepStageValue(
    val dateRange: ClosedRange<ZonedDateTime>,
    val stage: SleepStage
): Serializable