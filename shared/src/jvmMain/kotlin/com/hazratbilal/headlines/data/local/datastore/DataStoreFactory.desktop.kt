package com.hazratbilal.headlines.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import okio.Path.Companion.toPath
import java.io.File

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath {
        File(System.getProperty("java.io.tmpdir"), DATA_STORE_FILE_NAME).absolutePath.toPath()
    }
}