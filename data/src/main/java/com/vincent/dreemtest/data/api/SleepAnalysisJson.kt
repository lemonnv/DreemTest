package com.vincent.dreemtest.data.api

import com.vincent.dreemtest.domain.entity.SleepAnalysis
import com.vincent.dreemtest.domain.entity.SleepStage
import com.vincent.dreemtest.domain.entity.SleepStageValue
import java.lang.RuntimeException
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class SleepAnalysisJson(
    val id: String,
    val timezone: String,
    val record_start_time: Long,
    val record_stop_time: Long,
    val sleep_start_time: Long,
    val sleep_stop_time: Long,
    val sleep_onset_duration: Long,
    val sleep_score: Float,
    val nb_stimulations: Int,
    val nb_moves: Int,
    val mean_heart_rate: Float,
    val mean_respiration_asleep: Float,
    val hypnogram: List<Int>
) {

    internal fun toEntity(): SleepAnalysis {
        val zoneId = ZoneId.of(timezone)
        return SleepAnalysis(
            id = id,
            recordDateRange = zoneDateTimeOf(
                record_start_time,
                zoneId
            )..zoneDateTimeOf(
                record_stop_time,
                zoneId
            ),
            sleepDateRange = zoneDateTimeOf(
                sleep_start_time,
                zoneId
            )..zoneDateTimeOf(
                sleep_stop_time,
                zoneId
            ),
            sleepOnsetDuration = Duration.ofSeconds(sleep_onset_duration),
            score = sleep_score,
            numberOfStimulation = nb_stimulations,
            numberOfMove = nb_moves,
            heartRateAverage = mean_heart_rate,
            hypnogram = buildHypnogram()
        )
    }

    private fun buildHypnogram(): List<SleepStageValue> {
        val zoneId = ZoneId.of(timezone)
        return hypnogram.mapIndexed { index: Int, value: Int ->
            SleepStageValue(
                zoneDateTimeOf(
                    record_start_time + index * 30L,
                    zoneId
                )..zoneDateTimeOf(
                    record_start_time + (index + 1) * 30,
                    zoneId
                ),
                sleepStageOf(value)
            )
        }
    }

}

internal fun sleepStageOf(value: Int) = when (value) {
    -1 -> SleepStage.UNKNOWN
    0 -> SleepStage.WAKE
    1, 2 -> SleepStage.LIGHT_SLEEP
    3 -> SleepStage.DEEP_SLEEP
    4 -> SleepStage.REM
    else -> throw RuntimeException("invalid sleep stage value: $value")
}

private fun zoneDateTimeOf(timestamp: Long, zoneId: ZoneId) = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zoneId)!!


