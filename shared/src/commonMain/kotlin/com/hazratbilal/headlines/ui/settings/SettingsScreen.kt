package com.hazratbilal.headlines.ui.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hazratbilal.headlines.ui.settings.component.BookmarkDialog
import com.hazratbilal.headlines.ui.settings.component.SettingItem
import com.hazratbilal.headlines.ui.settings.component.ThemeSelectionDialog
import com.hazratbilal.headlines.utils.Dimens
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.delete_bookmark
import headlines.shared.generated.resources.ic_delete
import headlines.shared.generated.resources.ic_light_mode
import headlines.shared.generated.resources.theme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    viewModel: SettingsViewModel = koinViewModel()
) {
    var showThemeDialog by remember { mutableStateOf(false) }
    var showDeleteBookmarkDialog by remember { mutableStateOf(false) }
    val currentTheme by viewModel.themeFlow.collectAsState()
    val scope = rememberCoroutineScope()

    if (showThemeDialog) {
        ThemeSelectionDialog(
            currentTheme = currentTheme,
            onThemeChange = { selectedTheme ->
                viewModel.changeTheme(selectedTheme.name)
                showThemeDialog = false
                scope.launch {
                    snackbarHostState.showSnackbar("Application theme changed")
                }
            },
            onDismissRequest = { showThemeDialog = false }
        )
    }

    if (showDeleteBookmarkDialog) {
        BookmarkDialog(
            onConfirm = {
                viewModel.deleteAllBookmarks()
                showDeleteBookmarkDialog = false
                scope.launch {
                    snackbarHostState.showSnackbar("All bookmarks deleted")
                }
            },
            onDismiss = { showDeleteBookmarkDialog = false }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            Text(
                text = "Appearance",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(
                    horizontal = Dimens.mediumPadding,
                    vertical = Dimens.xSmallPadding
                )
            )
        }
        item {
            SettingItem(
                onClick = { showThemeDialog = true },
                painter = painterResource(Res.drawable.ic_light_mode),
                label = stringResource(Res.string.theme)
            )
        }
        item {
            SettingItem(
                onClick = { showDeleteBookmarkDialog = true },
                painter = painterResource(Res.drawable.ic_delete),
                label = stringResource(Res.string.delete_bookmark),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}