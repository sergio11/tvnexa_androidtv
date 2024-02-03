package com.dreamsoftware.tvnexa.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onGoToOnboarding: () -> Unit,
    onGoToHome: () -> Unit,
    onGoToProfileSelector: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SplashUiState() },
        onSideEffect = {
            when(it) {
                SplashSideEffects.UserAlreadyAuthenticated -> onGoToHome()
                SplashSideEffects.UserNotAuthenticated -> onGoToOnboarding()
            }
        },
        onInit = {
            verifySession()
        }
    ) { uiState ->
        SplashScreenContent(
            modifier = modifier,
            uiState = uiState
        )
    }
}