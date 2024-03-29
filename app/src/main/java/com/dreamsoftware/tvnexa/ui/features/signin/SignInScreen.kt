package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onGoToProfileSelector: () -> Unit,
    onGoToSignUp: () -> Unit,
    onBackPressed: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SignInUiState() },
        onSideEffect = {
            when(it) {
                SignInSideEffects.AuthenticationSuccessfully -> onGoToHome()
                SignInSideEffects.ProfileSelectionRequired -> onGoToProfileSelector()
            }
        }
    ) { uiState ->
        SignInScreenContent(
            uiState = uiState,
            onEmailChanged = ::onEmailChanged,
            onPasswordChanged = ::onPasswordChanged,
            onErrorAcceptPressed = ::onErrorAccepted,
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
            onGoToHome = {},
            onGoToSignUp = {},
            onGoToProfileSelector = {},
            onBackPressed = {}
        )
    }
}
