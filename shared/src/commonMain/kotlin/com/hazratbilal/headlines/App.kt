package com.hazratbilal.headlines

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hazratbilal.headlines.di.initKoin
import com.hazratbilal.headlines.theme.AppTheme
import com.hazratbilal.headlines.ui.MainScreen
import com.hazratbilal.headlines.ui.settings.SettingsViewModel
import com.hazratbilal.headlines.utils.Theme
import com.hazratbilal.headlines.utils.initCoil
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.KoinContext


@Composable
fun App() {
    remember {
        initKoin()
        initCoil()
    }

    KoinContext {
        val settingsViewModel: SettingsViewModel = koinViewModel()
        val themeMode by settingsViewModel.themeFlow.collectAsStateWithLifecycle()

        val isDarkTheme = when (themeMode) {
            Theme.DARK_MODE.name -> true
            Theme.LIGHT_MODE.name -> false
            else -> isSystemInDarkTheme()
        }

        AppTheme(isDarkTheme = isDarkTheme) {
            MainScreen()
        }
    }
}