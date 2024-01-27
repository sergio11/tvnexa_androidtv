package com.dreamsoftware.tvnexa.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.ConsumeSideEffects
import com.dreamsoftware.tvnexa.ui.components.produceUiState
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onGoToOnboarding: () -> Unit,
    onGoToHome: () -> Unit,
    onGoToProfileSelector: () -> Unit
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel
        ) {
            when(it) {
                SplashSideEffects.UserAlreadyAuthenticated -> onGoToHome()
                SplashSideEffects.UserNotAuthenticated -> onGoToOnboarding()
            }
        }
        val uiState by produceUiState(
            initialState = SplashUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {
            delay(4000)
            verifySession()
        }

        SplashScreenContent(
            modifier = modifier,
            uiState = uiState
        )
    }
}