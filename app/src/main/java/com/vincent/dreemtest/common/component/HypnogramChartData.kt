package com.vincent.dreemtest.common.component

import android.content.Context
import androidx.annotation.ColorInt
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.vincent.dreemtest.common.ext.toColor
import com.vincent.dreemtest.domain.entity.HypnogramSlice
import com.vincent.dreemtest.domain.entity.SleepStage

class HypnogramChartData private constructor(dataSets: List<LineDataSet>): LineData(dataSets) {

    companion object Factory {

        fun create(context: Context, hypnogram: List<HypnogramSlice>): HypnogramChartData {
            val dataSets = hypnogram.mapNotNull { if (it.stage != SleepStage.UNKNOWN) createLine(it, it.stage.toColor(context)) else null }
            return HypnogramChartData(dataSets)
        }

        private fun createLine(slice: HypnogramSlice, @ColorInt color: Int): LineDataSet = LineDataSet(createEntries(slice), "").apply {
            this.color = color
            lineWidth = 10f
            setDrawValues(false)
            setDrawIcons(false)
            setDrawCircles(false)
        }

        private fun createEntries(slice: HypnogramSlice): List<Entry> = listOf(
            Entry(slice.dateRange.start.toEpochSecond().toFloat(), slice.stage.toEntryY()),
            Entry(slice.dateRange.endInclusive.toEpochSecond().toFloat(), slice.stage.toEntryY())
        )

    }
}

private fun SleepStage.toEntryY(): Float = when (this) {
    SleepStage.WAKE -> 4f
    SleepStage.REM -> 3f
    SleepStage.LIGHT_SLEEP -> 2f
    SleepStage.DEEP_SLEEP -> 1f
    SleepStage.UNKNOWN -> 0f
}