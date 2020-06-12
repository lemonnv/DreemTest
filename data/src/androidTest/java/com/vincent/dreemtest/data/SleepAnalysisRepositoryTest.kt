package com.vincent.dreemtest.data

import com.vincent.dreemtest.domain.repository.SleepAnalysisRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class SleepAnalysisRepositoryTest: KoinTest {

    private val repository: SleepAnalysisRepository by inject()

    @Before
    fun setup() {
        startKoin {
            modules(dataModule)
        }
    }


    @Test
    fun getSleepAnalysisIsCorrect() {
        repository.getSleepAnalysis()
    }
}