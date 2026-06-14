package com.hazratbilal.headlines.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hazratbilal.headlines.navigation.NavGraph
import com.hazratbilal.headlines.navigation.NavigationBottomBar
import com.hazratbilal.headlines.navigation.NavigationItem
import com.hazratbilal.headlines.navigation.NavigationSideBar
import com.hazratbilal.headlines.navigation.Route
import com.hazratbilal.headlines.utils.navigationItemsLists
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen() {

    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val currentRoute: Route = when {
        currentDestination?.route?.contains("Headlines") == true -> Route.Headlines
        currentDestination?.route?.contains("Search") == true -> Route.Search
        currentDestination?.route?.contains("Bookmark") == true -> Route.Bookmark
        currentDestination?.route?.contains("Details") == true -> Route.Details
        currentDestination?.route?.contains("Settings") == true -> Route.Settings
        else -> Route.Headlines
    }

    val title = when (currentRoute) {
        Route.Headlines -> "Headlines"
        Route.Search -> "Search"
        Route.Bookmark -> "Bookmark"
        Route.Details -> "Details"
        Route.Settings -> "Settings"
    }

    val isBottomBarVisible = currentRoute != Route.Details && currentRoute != Route.Settings

    val onNavItemClick: (NavigationItem) -> Unit = { item ->
        rootNavController.navigate(item.route) {
            popUpTo(Route.Headlines) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // 600dp is the standard Material breakpoint for compact vs medium
        val isExpandedScreen = maxWidth >= 600.dp

        Row(modifier = Modifier.fillMaxSize()) {
            // Sidebar for expanded screens (tablet/desktop)
            AnimatedVisibility(visible = isExpandedScreen && isBottomBarVisible) {
                NavigationSideBar(
                    items = navigationItemsLists,
                    currentRoute = currentRoute,
                    onItemClick = onNavItemClick
                )
            }

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    when (currentRoute) {
                        Route.Details -> {}
                        else ->  TopAppBar(
                            title = {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            },
                            navigationIcon = {
                                if (currentRoute == Route.Settings) {
                                    IconButton(onClick = {
                                        rootNavController.popBackStack()
                                    }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            },
                            actions = {
                                if (currentRoute != Route.Settings) {
                                    IconButton(onClick = {
                                        rootNavController.navigate(Route.Settings)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Settings,
                                            contentDescription = stringResource(Res.string.setting)
                                        )
                                    }
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = !isExpandedScreen && isBottomBarVisible,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        NavigationBottomBar(
                            items = navigationItemsLists,
                            currentRoute = currentRoute,
                            onItemClick = onNavItemClick
                        )
                    }
                }
            ) { innerPadding ->
                NavGraph(
                    rootNavController = rootNavController,
                    innerPadding = innerPadding,
                    snackbarHostState = snackbarHostState
                )
            }

        }
    }
}