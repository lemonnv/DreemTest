package com.vincent.dreemtest.data.repository

import com.vincent.dreemtest.data.api.SleepService
import com.vincent.dreemtest.domain.entity.SleepAnalysis
import com.vincent.dreemtest.domain.repository.SleepAnalysisRepository

class SleepAnalysisRepositoryImpl(private val remoteService: SleepService): SleepAnalysisRepository {

    override fun getSleepAnalysis(): SleepAnalysis {
       return remoteService.getSleepAnalysis().execute().body()!!.toEntity()
    }

}
