package com.vincent.dreemtest.domain

import com.vincent.dreemtest.domain.usecase.GetNights
import com.vincent.dreemtest.domain.usecase.IsSignedIn
import org.koin.dsl.module

val domainModule = module {

    // UseCase
    factory { IsSignedIn() }
    factory { GetNights() }
}