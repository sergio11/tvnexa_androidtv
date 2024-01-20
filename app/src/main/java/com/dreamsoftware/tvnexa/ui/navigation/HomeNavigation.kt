package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.favorites.FavoritesScreen
import com.dreamsoftware.tvnexa.ui.features.channels.ChannelsScreen
import com.dreamsoftware.tvnexa.ui.features.movies.MoviesScreen
import com.dreamsoftware.tvnexa.ui.features.search.SearchScreen
import com.dreamsoftware.tvnexa.ui.features.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Home.Channels.path) {
        composable(
            Screens.Home.Channels.path,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            ChannelsScreen(onItemFocus = { _, _ -> }, onItemClick = onItemClick)
        }

        composable(
            Screens.Home.Search.path,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SearchScreen()
        }

        composable(
            Screens.Home.Movies.path,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            MoviesScreen(onItemClick)
        }

        composable(
            Screens.Home.Favorites.path,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            FavoritesScreen()
        }

        composable(
            Screens.Home.Settings.path,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SettingsScreen()
        }
    }
}
