package com.vincent.dreemtest.data

import com.vincent.dreemtest.domain.repository.NightRepository
import org.junit.Before
import org.junit.Test

import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class NightRepositoryTest: KoinTest {

    private val repository: NightRepository by inject()

    @Before
    fun setup() {
        startKoin {
            modules(dataModule)
        }
    }


    @Test
    fun getAllNightsIsCorrect() {
        repository.getAllNights()
    }
}