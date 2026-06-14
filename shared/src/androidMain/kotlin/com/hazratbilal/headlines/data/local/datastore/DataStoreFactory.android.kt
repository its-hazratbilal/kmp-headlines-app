package com.hazratbilal.headlines.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    return (context as Context).dataStore
}

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = DATA_STORE_FILE_NAME)