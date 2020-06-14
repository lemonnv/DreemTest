package com.vincent.dreemtest.domain.usecase

import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.domain.functional.Interactor
import com.vincent.dreemtest.domain.repository.NightRepository
import org.koin.core.inject

class GetNights: Interactor<List<Night>, Unit>() {

    private val repository: NightRepository by inject()

    override suspend fun runOnBackground(params: Unit): List<Night> {
        return repository.getAllNights()
    }

}