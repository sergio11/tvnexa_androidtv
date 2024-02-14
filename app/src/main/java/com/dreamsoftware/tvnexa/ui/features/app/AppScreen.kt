@file:OptIn(ExperimentalAnimationApi::class)

package com.dreamsoftware.tvnexa.ui.features.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.navigation.MainNavigation
import com.dreamsoftware.tvnexa.ui.navigation.Screens
import com.dreamsoftware.tvnexa.ui.navigation.extensions.navigateSingleTopTo
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun AppScreen(
    navController: NavHostController = rememberAnimatedNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    TvNexaTheme {
        CommonScreen(
            viewModel = viewModel,
            onInitialUiState = { AppUiState() },
            onSideEffect = {
                with(navController) {
                    val destination: String = when(it) {
                        AppSideEffects.ComeFromStandby -> Screens.Profiles.DEFAULT.path
                        AppSideEffects.NoSessionActive -> Screens.Onboarding.path
                    }
                    navigateSingleTopTo(destination)
                }

            }
        ) {
            MainNavigation(navController)
        }
    }
}