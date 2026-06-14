package com.hazratbilal.headlines.ui.headlines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hazratbilal.headlines.ui.common.ArticlesListScreen
import com.hazratbilal.headlines.ui.common.EmptyStateScreen
import com.hazratbilal.headlines.ui.common.ShimmerEffect
import com.hazratbilal.headlines.utils.Dimens
import com.hazratbilal.headlines.utils.categoryList
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.ic_headline
import headlines.shared.generated.resources.ic_network_error
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HeadlinesScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
    viewModel: HeadlinesViewModel = koinViewModel()
) {
    val uiState by viewModel.articlesStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(Dimens.xSmallPadding)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.xSmallPadding),
            contentPadding = PaddingValues(horizontal = Dimens.mediumPadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.xSmallPadding, Alignment.CenterHorizontally)
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    selected = viewModel.selectedCategory == category,
                    onClick = {
                        viewModel.selectedCategory = category
                        viewModel.getHeadlines(viewModel.selectedCategory)
                    }, label = {
                        Text(category)
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }

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
                    EmptyStateScreen(drawable = Res.drawable.ic_headline, message = "No articles found")
                }

            },
            onError = { message ->
                EmptyStateScreen(drawable = Res.drawable.ic_network_error, message = message)
            }
        )

    }
}