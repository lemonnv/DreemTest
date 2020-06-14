package com.vincent.dreemtest.dashboard

import androidx.lifecycle.MutableLiveData
import com.vincent.dreemtest.common.BaseViewModel
import com.vincent.dreemtest.common.SingleLiveEvent
import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.domain.usecase.GetNights
import org.koin.core.inject

class DashboardViewModel: BaseViewModel() {

    sealed class State {
        object Loading: State()
        object Idle: State()
    }

    val state = MutableLiveData<State>()

    sealed class Intent {
        data class ShowNightDetails(val night: Night): Intent()
        data class ShowToast(val error: Exception): Intent()
    }

    val intent = SingleLiveEvent<Intent>()

    val nights = MutableLiveData<List<Night>>(listOf())

    private val getNights: GetNights by inject()

    fun load() {
        state.value = State.Loading
        getNights.executeInViewModelScope {
            state.value = State.Idle
            nights.value = it.getOrNull() ?: listOf()
        }
    }

    fun select(id: String) {
        nights.value?.firstOrNull { it.id == id }?.apply {
            intent.value = Intent.ShowNightDetails(this)
        }
    }

}