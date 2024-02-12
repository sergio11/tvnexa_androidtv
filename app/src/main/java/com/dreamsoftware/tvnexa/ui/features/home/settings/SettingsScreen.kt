package com.dreamsoftware.tvnexa.ui.features.home.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val navController = rememberAnimatedNavController()
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SettingsUiState() }
    ) {
        SettingsScreenContent(
            navController = navController
        )
    }
}