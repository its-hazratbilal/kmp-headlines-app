package com.hazratbilal.headlines.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.navigation.Route
import com.hazratbilal.headlines.utils.Dimens
import com.hazratbilal.headlines.utils.getGridColumns
import kotlinx.serialization.json.Json

@Composable
fun ArticlesListScreen(rootNavController: NavController, articles: List<Article>) {
    BoxWithConstraints {
        val columns = getGridColumns(maxWidth.value.toInt())

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(Dimens.largePadding),
            horizontalArrangement = Arrangement.spacedBy(Dimens.xLargePadding),
            contentPadding = PaddingValues(
                start = Dimens.mediumPadding,
                end = Dimens.mediumPadding
            )
        ) {
            items(articles) { article ->
                ArticleItem(article = article, onClick = {
                    val articleStr = Json.encodeToString(article)
                    rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("article", articleStr)
                    }
                    rootNavController.navigate(Route.Details)
                })
            }
        }
    }
}