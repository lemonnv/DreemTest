package com.vincent.dreemtest.data.api

import retrofit2.Call
import retrofit2.http.GET

interface NightService {
    @GET("test1.json")
    fun getLastNight(): Call<NightJson>
}