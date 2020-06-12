package com.vincent.dreemtest

import android.app.Application
import com.vincent.dreemtest.data.dataModule
import com.vincent.dreemtest.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(appModule, domainModule, dataModule)
        }
    }
}