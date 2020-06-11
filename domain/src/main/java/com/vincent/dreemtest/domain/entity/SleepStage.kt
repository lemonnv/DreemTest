package com.vincent.dreemtest.domain.entity

import java.time.ZonedDateTime

enum class SleepStage {
    UNKNOWN, WAKE, LIGHT_SLEEP, DEEP_SLEEP, REM
}

data class SleepStageValue(
    val dateRange: ClosedRange<ZonedDateTime>,
    val stage: SleepStage
)