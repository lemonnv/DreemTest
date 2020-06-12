package com.vincent.dreemtest.domain.repository

import com.vincent.dreemtest.domain.entity.Night

interface NightRepository {
    fun getAllNights(): List<Night>
}