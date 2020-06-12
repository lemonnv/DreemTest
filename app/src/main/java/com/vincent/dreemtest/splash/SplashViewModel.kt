package com.vincent.dreemtest.splash

import androidx.lifecycle.viewModelScope
import com.vincent.dreemtest.common.BaseViewModel
import com.vincent.dreemtest.common.SingleLiveEvent
import com.vincent.dreemtest.domain.usecase.IsSignedIn
import com.vincent.dreemtest.domain.functional.execute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject

class SplashViewModel: BaseViewModel() {

    sealed class Intent {
        object ShowDashboard: Intent()
        object ShowOnboarding: Intent()
    }

    val intent = SingleLiveEvent<Intent>()

    private val isSignedIn: IsSignedIn by inject()

    init {
        viewModelScope.launch {
            delay(2000)
            if (isSignedIn.execute().getOrNull() == true) {
                intent.value = Intent.ShowDashboard
            } else {
                intent.value = Intent.ShowOnboarding
            }
        }


    }

}