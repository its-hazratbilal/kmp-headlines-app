package com.hazratbilal.headlines.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hazratbilal.headlines.utils.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val THEME_KEY = stringPreferencesKey("theme_key")
    }

    val themeFlow: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: Theme.SYSTEM_DEFAULT.name
        }

    suspend fun getInitialTheme(): String {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: Theme.SYSTEM_DEFAULT.name
        }.first()
    }

    suspend fun changeTheme(themeName: String) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = themeName
        }
    }
}