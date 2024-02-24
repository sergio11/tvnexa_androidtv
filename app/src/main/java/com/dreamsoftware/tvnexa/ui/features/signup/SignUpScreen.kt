package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SignUpUiState() },
        onSideEffect = {
            if(it is SignUpSideEffects.RegisteredSuccessfully) {
                onBackPressed()
            }
        }
    ) { uiState ->
        SignUpScreenContent(
            uiState = uiState,
            onFirstNameChanged = ::onFirstNameChanged,
            onLastNameChanged = ::onLastNameChanged,
            onEmailChanged = ::onEmailChanged,
            onUsernameChanged = ::onUsernameChanged,
            onPasswordChanged = ::onPasswordChanged,
            onRepeatPasswordChanged = ::onRepeatPasswordChanged,
            onSigUpPressed = ::onSignUp,
            onCancelPressed = onBackPressed
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpScreenPrev() {
    TvNexaTheme {
        SignUpScreen(
            onBackPressed = {}
        )
    }
}
