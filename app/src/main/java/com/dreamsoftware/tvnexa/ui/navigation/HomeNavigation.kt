package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.channels.ChannelsScreen
import com.dreamsoftware.tvnexa.ui.features.favorites.FavoritesScreen
import com.dreamsoftware.tvnexa.ui.features.search.SearchScreen
import com.dreamsoftware.tvnexa.ui.features.settings.SettingsScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    onNavigateToDetail: (String) -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Home.Channels.path) {

        transitionComposable(Screens.Home.Channels.path) {
            ChannelsScreen(onGoToChannelDetail = onNavigateToDetail)
        }

        transitionComposable(Screens.Home.Search.path) {
            SearchScreen(onGoToChannelDetail = onNavigateToDetail)
        }

        transitionComposable(Screens.Home.Favorites.path) {
            FavoritesScreen(onGoToChannelDetail = onNavigateToDetail)
        }

        transitionComposable(Screens.Home.Settings.DEFAULT.path) {
            SettingsScreen()
        }
    }
}
