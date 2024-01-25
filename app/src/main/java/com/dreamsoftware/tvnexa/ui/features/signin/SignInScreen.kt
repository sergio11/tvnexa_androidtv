package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.ConsumeSideEffects
import com.dreamsoftware.tvnexa.ui.components.produceUiState
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel
        ) {
            if(it is SignInSideEffects.AuthenticationSuccessfully) {
                onGoToHome()
            }
        }

        val snackBarHostState = remember { SnackbarHostState() }
        val uiState by produceUiState(
            initialState = SignInUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        SignInScreenContent(
            modifier = modifier,
            uiState = uiState,
            snackBarHostState = snackBarHostState,
            onEmailChanged = ::onEmailChanged,
            onPasswordChanged = ::onPasswordChanged,
            onSigInPressed = ::onSignIn,
            onGoToSignUp = onGoToSignUp
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignInScreenPrev() {
    TvNexaTheme {
        SignInScreen(
            modifier = Modifier.fillMaxSize(),
            onGoToHome = {},
            onGoToSignUp = {}
        )
    }
}
