package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.about.AboutScreen
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.profile.ProfileScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingsNavigation(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Home.Settings.DEFAULT.path,
    ) {
        transitionComposable(Screens.Home.Settings.Profile.path) {
            ProfileScreen()
        }
        composable(Screens.Home.Settings.AboutMe.path) {
            AboutScreen()
        }
    }
}
