@file:OptIn(ExperimentalAnimationApi::class)

package com.dreamsoftware.tvnexa.ui.features.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.extensions.openSystemSettings
import com.dreamsoftware.tvnexa.ui.extensions.restartApplication
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
            with(LocalContext.current) {
                AppScreenContent(
                    uiState = it,
                    navController = navController,
                    onOpenSettingsPressed = {
                        openSystemSettings()
                    },
                    onRestartAppPressed = {
                        restartApplication()
                    },
                    onErrorAccepted = ::onErrorAccepted
                )
            }
        }
    }
}