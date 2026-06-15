package com.hazratbilal.headlines.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import com.hazratbilal.headlines.navigation.NavigationItem
import com.hazratbilal.headlines.navigation.Route
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.*
import org.jetbrains.compose.resources.StringResource

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "YOUR_API_KEY"

val categoryList = arrayListOf(
    "All",
    "Business",
    "Entertainment",
    "General",
    "Health",
    "Science",
    "Sports",
    "Technology"
)

fun calculateColumns(windowWidth: Int): Int = when {
    windowWidth >= 1200 -> 3
    windowWidth >= 600 -> 2
    else -> 1
}

val navigationItemsLists = listOf(
    NavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines,
        route = Route.Headlines
    ),
    NavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = Route.Search,
    ),
    NavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark,
        route = Route.Bookmark,
    ),
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}
