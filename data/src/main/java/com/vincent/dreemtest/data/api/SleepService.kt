package com.vincent.dreemtest.data.api

import com.vincent.dreemtest.data.api.SleepAnalysisJson
import retrofit2.Call
import retrofit2.http.GET

interface SleepService {
    @GET("test1.json")
    fun getSleepAnalysis(): Call<SleepAnalysisJson>
}