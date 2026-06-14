package com.hazratbilal.headlines.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hazratbilal.headlines.ui.common.ArticlesListScreen
import com.hazratbilal.headlines.ui.common.EmptyStateScreen
import com.hazratbilal.headlines.ui.common.ShimmerEffect
import com.hazratbilal.headlines.utils.Dimens
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.ic_headline
import headlines.shared.generated.resources.ic_network_error
import headlines.shared.generated.resources.ic_search
import headlines.shared.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = koinViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.articlesStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.mediumPadding,
                    end = Dimens.mediumPadding
                ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = stringResource(Res.string.search),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            },
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchHeadlines(searchText)
                    focusManager.clearFocus()
                }
            )
        )

        uiState.DisplayResult(
            onIdle = {
                EmptyStateScreen(drawable = Res.drawable.ic_search, message = "Search for articles")
            },
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
                    EmptyStateScreen(drawable = Res.drawable.ic_headline, message = "No results found")
                }
            },
            onError = { message ->
                EmptyStateScreen(drawable = Res.drawable.ic_network_error, message = message)
            }
        )
    }
}