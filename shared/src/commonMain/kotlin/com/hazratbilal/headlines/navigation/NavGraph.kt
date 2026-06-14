package com.hazratbilal.headlines.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.ui.bookmark.BookmarkScreen
import com.hazratbilal.headlines.ui.details.DetailsScreen
import com.hazratbilal.headlines.ui.headlines.HeadlinesScreen
import com.hazratbilal.headlines.ui.search.SearchScreen
import com.hazratbilal.headlines.ui.settings.SettingsScreen
import kotlinx.serialization.json.Json

@Composable
fun NavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Headlines,
    ) {
        composable<Route.Headlines> {
            HeadlinesScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Search> {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Bookmark> {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Details> {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                DetailsScreen(rootNavController = rootNavController, snackbarHostState = snackbarHostState, article = currentArticle)
            }
        }
        composable<Route.Settings> {
            SettingsScreen(paddingValues = innerPadding, snackbarHostState = snackbarHostState)
        }

    }
}