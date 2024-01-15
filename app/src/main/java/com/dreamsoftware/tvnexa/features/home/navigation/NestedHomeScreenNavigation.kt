package com.dreamsoftware.tvnexa.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.dreamsoftware.tvnexa.features.favorites.FavoritesScreen
import com.dreamsoftware.tvnexa.features.home.HomeNestedScreen
import com.dreamsoftware.tvnexa.features.movies.MoviesScreen
import com.dreamsoftware.tvnexa.features.search.SearchScreen
import com.dreamsoftware.tvnexa.features.settings.SettingsScreen
import com.dreamsoftware.tvnexa.navigation.tabEnterTransition
import com.dreamsoftware.tvnexa.navigation.tabExitTransition
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedHomeScreenNavigation(
    usedTopBar: StateFlow<Boolean>,
    toggleTopBar: () -> Unit,
    navController: NavHostController,
    onItemClick: (parent: Int, child: Int) -> Unit,
    onSongClick: () -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = NestedScreens.Home.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            NestedScreens.Home.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            HomeNestedScreen(onItemFocus = { _, _ -> }, onItemClick = onItemClick)
        }

        composable(
            NestedScreens.Search.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SearchScreen()
        }

        composable(
            NestedScreens.Movies.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            MoviesScreen(onItemClick)
        }

        composable(
            NestedScreens.Favorites.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            FavoritesScreen()
        }

        composable(
            NestedScreens.Settings.title,
            enterTransition = { tabEnterTransition() },
            exitTransition = { tabExitTransition() }) {
            SettingsScreen(toggleTopBar = toggleTopBar, usedTopBar = usedTopBar)
        }
    }
}
