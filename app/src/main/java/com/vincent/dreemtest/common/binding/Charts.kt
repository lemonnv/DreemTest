package com.vincent.dreemtest.common.binding

import androidx.databinding.BindingAdapter
import com.vincent.dreemtest.common.component.HypnogramChartData
import com.vincent.dreemtest.common.component.HypnogramChartView
import com.vincent.dreemtest.domain.entity.HypnogramSlice

@BindingAdapter("data")
fun setHypnogramData(hypnogramChartView: HypnogramChartView, data: List<HypnogramSlice>) {
    hypnogramChartView.data = HypnogramChartData.create(hypnogramChartView.context, data)
}