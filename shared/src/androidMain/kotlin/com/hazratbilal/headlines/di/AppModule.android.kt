package com.hazratbilal.headlines.di

import com.hazratbilal.headlines.data.local.datastore.createDataStore
import com.hazratbilal.headlines.data.local.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { DatabaseDriverFactory(androidContext()) }
    single { createDataStore(androidContext()) }
}