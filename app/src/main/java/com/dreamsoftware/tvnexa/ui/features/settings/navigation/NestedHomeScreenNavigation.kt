package com.dreamsoftware.tvnexa.ui.features.settings.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.settings.screens.about.AboutScreen
import com.dreamsoftware.tvnexa.ui.features.settings.screens.profile.ProfileScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NestedSettingsScreenNavigation(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = SettingsScreens.Profile.title,
    ) {
        transitionComposable(SettingsScreens.Profile.title) {
            ProfileScreen()
        }
        composable(SettingsScreens.AboutMe.title) {
            AboutScreen()
        }
    }
}
