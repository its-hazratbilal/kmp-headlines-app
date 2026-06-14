package com.hazratbilal.headlines.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hazratbilal.headlines.ui.common.ArticlesListScreen
import com.hazratbilal.headlines.ui.common.EmptyStateScreen
import com.hazratbilal.headlines.ui.common.ShimmerEffect
import com.hazratbilal.headlines.utils.Dimens
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.ic_bookmark_outlined
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookmarkScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
    viewModel: BookmarkViewModel = koinViewModel()
) {
    val uiState by viewModel.bookmarks.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = Dimens.mediumPadding),
    ) {
        uiState.DisplayResult(
            onIdle = {},
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { articles ->
                if (articles.isNotEmpty()) {
                    ArticlesListScreen(
                        rootNavController = rootNavController,
                        articles = articles
                    )
                } else {
                    EmptyStateScreen(drawable = Res.drawable.ic_bookmark_outlined, message = "No bookmarks yet")
                }
            },
            onError = { message ->
                EmptyStateScreen(drawable = Res.drawable.ic_bookmark_outlined, message = message)
            }
        )
    }
}