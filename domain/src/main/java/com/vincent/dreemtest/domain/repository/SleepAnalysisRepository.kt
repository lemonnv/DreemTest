package com.vincent.dreemtest.domain.repository

import com.vincent.dreemtest.domain.entity.SleepAnalysis

interface SleepAnalysisRepository {
    fun getSleepAnalysis(): List<SleepAnalysis>
}