package com.vincent.dreemtest.domain.usecase

import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.domain.functional.Interactor
import com.vincent.dreemtest.domain.repository.NightRepository
import org.koin.core.inject

class GetNights: Interactor<List<Night>, Void>() {

    private val repository: NightRepository by inject()

    override suspend fun runOnBackground(params: Void): List<Night> {
        return repository.getAllNights()
    }

}