package com.vincent.dreemtest.data

import com.vincent.dreemtest.data.api.NightJson
import com.vincent.dreemtest.data.api.sleepStageOf
import org.junit.Test

import org.junit.Assert.*
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NightMapperUnitTest {

    @Test
    fun hypnogramIsEmpty() {
        val json = createJson(listOf())
        val entity = json.toEntity()
        assertEquals(0, entity.hypnogram.size)
    }

    @Test
    fun hypnogramOfFiveMinutesIsCorrect() {
        val json = createJson(listOf(0, 0, 1, 1, 2, 2, 3, 3, -1, -1))
        val entity = json.toEntity()
        assertEquals(json.hypnogram.size, entity.hypnogram.size)
        for (i in entity.hypnogram.indices) {
            assertEquals(
                json.record_start_time + i * 30,
                entity.hypnogram[i].dateRange.start.toEpochSecond()
            )
            assertEquals(
                json.record_start_time + (i + 1) * 30,
                entity.hypnogram[i].dateRange.endInclusive.toEpochSecond()
            )
            assertEquals(sleepStageOf(json.hypnogram[i]), entity.hypnogram[i].stage)
        }
    }

    private fun createJson(hypnogram: List<Int>) =
        NightJson(
            "",
            ZoneId.systemDefault().id,
            ZonedDateTime.now().withSecond(0).toEpochSecond(),
            ZonedDateTime.now().withSecond(0).plusSeconds(hypnogram.size * 30L).toEpochSecond(),
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

}