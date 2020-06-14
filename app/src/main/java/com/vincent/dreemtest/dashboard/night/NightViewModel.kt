package com.vincent.dreemtest.dashboard.night

import androidx.lifecycle.MutableLiveData
import com.vincent.dreemtest.common.BaseViewModel
import com.vincent.dreemtest.domain.entity.Night
import java.time.LocalTime
import java.time.ZonedDateTime

class NightViewModel(entity: Night): BaseViewModel() {

    sealed class Intent {
        object Back: Intent()
    }

    val intent = MutableLiveData<Intent>()

    val date = MutableLiveData<ZonedDateTime>(entity.recordDateRange.endInclusive)

    val bedtime = MutableLiveData(entity.recordDateRange.start.toLocalTime())

    val wakeUpTime = MutableLiveData(entity.recordDateRange.endInclusive.toLocalTime())

    val fallAsleepTime = MutableLiveData(entity.sleepDateRange.start.toLocalTime())

    val awakeTime = MutableLiveData(entity.sleepDateRange.endInclusive.toLocalTime())

    val quality = MutableLiveData<Float>(entity.sleepScore)

    val onsetDuration = MutableLiveData(LocalTime.of(entity.sleepOnsetDuration.toHours().toInt(), entity.sleepOnsetDuration.toMinutes().toInt()))

    val numberOfMoves = MutableLiveData(entity.numberOfMove)

    val hypnogram = MutableLiveData(entity.hypnogram)

    fun close() {
        intent.value = Intent.Back
    }
}
