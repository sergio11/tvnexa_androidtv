package com.dreamsoftware.tvnexa.ui.features.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.favorites.FavoritesScreen
import com.dreamsoftware.tvnexa.ui.features.home.HomeNestedScreen
import com.dreamsoftware.tvnexa.ui.features.movies.MoviesScreen
import com.dreamsoftware.tvnexa.ui.features.search.SearchScreen
import com.dreamsoftware.tvnexa.ui.features.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.dreamsoftware.tvnexa.ui.navigation.tabEnterTransition
import com.dreamsoftware.tvnexa.ui.navigation.tabExitTransition
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedHomeScreenNavigation(
    usedTopBar: StateFlow<Boolean>,
    toggleTopBar: () -> Unit,
    navController: NavHostController,
    onItemClick: (parent: Int, child: Int) -> Unit
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
