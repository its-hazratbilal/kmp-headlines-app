package com.hazratbilal.headlines.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.hazratbilal.headlines.data.local.datastore.AppPreferences
import com.hazratbilal.headlines.data.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsViewModel(
    private val appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) : ViewModel() {

    val themeFlow = appPreferences.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = runBlocking { appPreferences.getInitialTheme() }
    )

    fun changeTheme(themeName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeTheme(themeName)
        }
    }

    fun deleteAllBookmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.deleteAllBookmarks()
        }
    }
}