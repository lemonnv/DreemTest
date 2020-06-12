package com.vincent.dreemtest.data.repository

import com.vincent.dreemtest.data.api.NightService
import com.vincent.dreemtest.domain.entity.Night
import com.vincent.dreemtest.domain.repository.NightRepository

class NightRepositoryImpl(private val remoteService: NightService): NightRepository {

    override fun getAllNights(): List<Night> {
        val lastNight = remoteService.getLastNight().execute().body()!!.toEntity()
        return listOf(lastNight)
    }

}
