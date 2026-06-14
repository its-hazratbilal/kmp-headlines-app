package com.hazratbilal.headlines.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.hazratbilal.headlines.data.model.Article
import com.hazratbilal.headlines.utils.Dimens
import com.hazratbilal.headlines.utils.shareUrl
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.ic_bookmark_filled
import headlines.shared.generated.resources.ic_bookmark_outlined
import headlines.shared.generated.resources.ic_browse
import headlines.shared.generated.resources.ic_headline
import headlines.shared.generated.resources.news_detail
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(
    rootNavController: NavController,
    snackbarHostState: SnackbarHostState,
    article: Article,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val isBookmarked by viewModel.isBookmarked.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(article.url) {
        viewModel.checkBookmark(article.url)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        rootNavController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val url = if (article.url.startsWith("http")) {
                            article.url
                        } else {
                            "https://${article.url}"
                        }
                        shareUrl(url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(onClick = {
                        val url = if (article.url.startsWith("http")) {
                            article.url
                        } else {
                            "https://${article.url}"
                        }
                        uriHandler.openUri(url)
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = "Browse"
                        )
                    }
                    IconButton(onClick = {
                        viewModel.toggleBookmark(article)
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (isBookmarked) "Bookmark removed" else "Article bookmarked"
                            )
                        }
                    }) {
                        Icon(
                            painter = if (isBookmarked)
                                painterResource(Res.drawable.ic_bookmark_filled)
                            else
                                painterResource(Res.drawable.ic_bookmark_outlined),
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.largePadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.imageHeight)
                    .clip(MaterialTheme.shapes.large)
                    .background(Color.Gray),
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(Res.drawable.ic_headline),
                placeholder = painterResource(Res.drawable.ic_headline),
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}