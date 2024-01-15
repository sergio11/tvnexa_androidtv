package com.dreamsoftware.tvnexa.features.home.navigation

sealed class NestedScreens(val title: String) {
    object Home : NestedScreens("home")
    object Search : NestedScreens("search")
    object Movies : NestedScreens("movies")
    object Songs : NestedScreens("songs")
    object Favorites : NestedScreens("favourites")
    object Settings : NestedScreens("settings")
}
