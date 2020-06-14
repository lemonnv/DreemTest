package com.vincent.dreemtest.common.component

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.mikephil.charting.charts.LineChart
import com.vincent.dreemtest.domain.entity.SleepStage

class HypnogramChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val lineChart = LineChart(context, attrs, defStyleAttr)

    init {
        lineChart.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(lineChart)
        setupChart()
    }

    private fun setupChart() {
        lineChart.description = null
        lineChart.setNoDataText(null)
        lineChart.legend.isEnabled = false
        lineChart.setDrawMarkers(false)
        lineChart.setDrawBorders(false)
        lineChart.axisRight.isEnabled = false
        lineChart.minOffset = 0f
        lineChart.setScaleEnabled(false)
        lineChart.isHighlightPerTapEnabled = false
        lineChart.isHighlightPerDragEnabled = false

        with(lineChart.axisLeft) {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawLabels(false)
            axisMinimum = 0f
            axisMaximum = SleepStage.values().size.toFloat()
        }

        with(lineChart.xAxis) {
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawLabels(false)
            position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
            yOffset = 0f
        }
    }

    var data: HypnogramChartData? = null
        set(value) {
            field = value
            lineChart.clear()
            lineChart.xAxis.resetAxisMinimum()
            lineChart.xAxis.resetAxisMaximum()
            value?.apply {
                lineChart.xAxis.axisMinimum = xMin
                lineChart.xAxis.axisMaximum = xMax
            }
            lineChart.data = value
        }
}