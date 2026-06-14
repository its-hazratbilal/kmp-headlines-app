package com.hazratbilal.headlines.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hazratbilal.headlines.data.local.datastore.AppPreferences
import com.hazratbilal.headlines.data.local.db.DatabaseDriverFactory
import com.hazratbilal.headlines.data.local.db.HeadlinesDatabase
import com.hazratbilal.headlines.data.remote.RemoteRepository
import com.hazratbilal.headlines.data.repository.LocalRepository
import com.hazratbilal.headlines.ui.bookmark.BookmarkViewModel
import com.hazratbilal.headlines.ui.details.DetailsViewModel
import com.hazratbilal.headlines.ui.headlines.HeadlinesViewModel
import com.hazratbilal.headlines.ui.search.SearchViewModel
import com.hazratbilal.headlines.ui.settings.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


expect val platformModule: Module

val commonModule = module {
    // DataStore
    single { AppPreferences(get()) }
    // SQLDelight
    single { HeadlinesDatabase(get<DatabaseDriverFactory>().createDriver()) }

    // Repository
    single { LocalRepository(get()) }
    single { RemoteRepository(get()) }

    // ViewModels
    viewModelOf(::HeadlinesViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::BookmarkViewModel)
}
