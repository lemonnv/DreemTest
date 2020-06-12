package com.vincent.dreemtest.dashboard

import androidx.lifecycle.MutableLiveData
import com.vincent.dreemtest.common.BaseViewModel
import com.vincent.dreemtest.common.SingleLiveEvent
import com.vincent.dreemtest.domain.entity.SleepAnalysis

class DashboardViewModel: BaseViewModel() {

    sealed class State {
        object Loading: State()
        object Idle: State()
    }

    val state = MutableLiveData<State>()

    sealed class Intent {
        data class ShowNightDetails(val analysis: SleepAnalysis)
    }

    val intent = SingleLiveEvent<Intent>()


}