package com.vincent.dreemtest

import com.vincent.dreemtest.dashboard.DashboardViewModel
import com.vincent.dreemtest.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModel
    viewModel { SplashViewModel() }
    viewModel { DashboardViewModel() }
}