package com.hazratbilal.headlines.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object Headlines : Route

    @Serializable
    data object Search : Route

    @Serializable
    data object Bookmark : Route

    @Serializable
    data object Details : Route

    @Serializable
    data object Settings : Route

}
