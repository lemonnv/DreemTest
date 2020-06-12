package com.vincent.dreemtest.domain.usecase

import com.vincent.dreemtest.domain.entity.SleepAnalysis
import com.vincent.dreemtest.domain.functional.Interactor
import com.vincent.dreemtest.domain.repository.SleepAnalysisRepository
import org.koin.core.inject

class GetSleepAnalysis: Interactor<SleepAnalysis, Void>() {

    private val repository: SleepAnalysisRepository by inject()

    override suspend fun runOnBackground(params: Void): SleepAnalysis {
        return repository.getSleepAnalysis()
    }

}