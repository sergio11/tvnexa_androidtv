package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.ui.navigation.ProfilesNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilesScreen(onGoToHome: () -> Unit) {
    val navController = rememberAnimatedNavController()
    ProfilesNavigation(navController = navController, onGoToHome = onGoToHome)
}