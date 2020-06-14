package com.vincent.dreemtest

import com.vincent.dreemtest.dashboard.DashboardViewModel
import com.vincent.dreemtest.dashboard.night.NightViewModel
import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModel
    viewModel { SplashViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { (night: Night) -> NightViewModel(night) }
}