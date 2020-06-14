package com.vincent.dreemtest.dashboard.night

import androidx.lifecycle.MutableLiveData
import com.vincent.dreemtest.common.BaseViewModel
import com.vincent.dreemtest.domain.entity.Night

class NightViewModel(entity: Night): BaseViewModel() {

    val title = MutableLiveData<String>()

    val quality = MutableLiveData<String>()

    fun close() {

    }
}
