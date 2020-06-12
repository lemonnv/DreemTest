package com.vincent.dreemtest.domain

import com.vincent.dreemtest.domain.usecase.GetNights
import org.koin.dsl.module

val domainModule = module {

    // UseCase
    factory { GetNights() }
}