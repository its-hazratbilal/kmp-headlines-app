package com.hazratbilal.headlines

import android.app.Application
import com.hazratbilal.headlines.di.commonModule
import com.hazratbilal.headlines.di.networkModule
import com.hazratbilal.headlines.di.platformModule
import com.hazratbilal.headlines.utils.initShareManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp: Application() {

    companion object {
        lateinit var instance: BaseApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initShareManager(this)

        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(networkModule, commonModule, platformModule)
        }
    }
}
