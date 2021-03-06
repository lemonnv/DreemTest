package com.vincent.dreemtest.data.api

import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.domain.entity.SleepStage
import com.vincent.dreemtest.domain.entity.HypnogramSlice
import java.lang.RuntimeException
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class NightJson(
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

    internal fun toEntity(): Night {
        val zoneId = ZoneId.of(timezone)
        return Night(
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
            sleepScore = sleep_score,
            numberOfStimulation = nb_stimulations,
            numberOfMove = nb_moves,
            heartRateAverage = mean_heart_rate,
            hypnogram = buildHypnogram()
        )
    }

    private fun buildHypnogram(): List<HypnogramSlice> {
        val zoneId = ZoneId.of(timezone)

        val result = mutableListOf<HypnogramSlice>()
        var i = 0
        while (i < hypnogram.size) {
            val start = record_start_time + i * 30L
            val stage = sleepStageOf(hypnogram[i])
            var j = i
            while (j < hypnogram.size && stage == sleepStageOf(hypnogram[j])) {
                ++j
            }
            val end = record_start_time + j * 30L
            result.add(HypnogramSlice(
                dateRange = zoneDateTimeOf(start, zoneId)..zoneDateTimeOf(end, zoneId),
                stage = stage
            ))
            i = j
        }
        return result
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


