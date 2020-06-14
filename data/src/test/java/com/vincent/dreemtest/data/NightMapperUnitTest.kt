package com.vincent.dreemtest.data

import com.vincent.dreemtest.data.api.NightJson
import com.vincent.dreemtest.data.api.sleepStageOf
import com.vincent.dreemtest.domain.entity.HypnogramSlice
import com.vincent.dreemtest.domain.entity.SleepStage
import org.junit.Test

import org.junit.Assert.*
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NightMapperUnitTest {

    private val zoneId = ZoneId.systemDefault()

    @Test
    fun hypnogramIsEmpty() {
        val json = createJson(listOf(), 0)
        val entity = json.toEntity()
        assertEquals(0, entity.hypnogram.size)
    }

    @Test
    fun hypnogramOfFiveMinutesIsCorrect() {
        val json = createJson(listOf(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, -1, -1), 0)
        val entity = json.toEntity()
        assertEquals(5, entity.hypnogram.size)
        assertEquals(createSlice(0, 60, SleepStage.WAKE), entity.hypnogram[0])
        assertEquals(createSlice(60, 180, SleepStage.LIGHT_SLEEP), entity.hypnogram[1])
        assertEquals(createSlice(180, 240, SleepStage.DEEP_SLEEP), entity.hypnogram[2])
        assertEquals(createSlice(240, 300, SleepStage.REM), entity.hypnogram[3])
        assertEquals(createSlice(300, 360, SleepStage.UNKNOWN), entity.hypnogram[4])
    }

    private fun createJson(hypnogram: List<Int>, epochSeconds: Long) =
        NightJson(
            "",
            zoneId.id,
            epochSeconds,
            epochSeconds + hypnogram.size * 30L,
            0,
            0,
            0,
            0.0f,
            0,
            0,
            0f,
            0f,
            hypnogram
        )

    private fun createSlice(start: Long, end: Long, stage: SleepStage): HypnogramSlice {
        val dateRange = ZonedDateTime.ofInstant(Instant.ofEpochSecond(start), zoneId)..ZonedDateTime.ofInstant(Instant.ofEpochSecond(end), zoneId)
        return HypnogramSlice(dateRange, stage)
    }


}