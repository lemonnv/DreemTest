package com.vincent.dreemtest.domain

import com.vincent.dreemtest.domain.usecase.GetSleepAnalysis
import org.koin.dsl.module

val domainModule = module {

    // UseCase
    factory { GetSleepAnalysis() }
}