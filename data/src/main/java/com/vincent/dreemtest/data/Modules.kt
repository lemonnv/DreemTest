package com.vincent.dreemtest.data

import com.vincent.dreemtest.data.api.NightService
import com.vincent.dreemtest.data.repository.NightRepositoryImpl
import com.vincent.dreemtest.domain.repository.NightRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    // API
    single<Retrofit> { Retrofit.Builder()
        .baseUrl("https://dreemtest.s3.us-east-2.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS })
            .build())
        .build()
    }
    factory { get<Retrofit>().create(NightService::class.java) }

    // Repository
    factory<NightRepository> { NightRepositoryImpl(get()) }

}