package com.hazratbilal.headlines.di

import org.koin.core.context.startKoin

actual fun initKoin() {
    startKoin {
        modules(networkModule, commonModule, platformModule)
    }
}