package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SignUpUiState() },
        onSideEffect = {
            if(it is SignUpSideEffects.RegisteredSuccessfully) {
                onBack()
            }
        }
    ) { uiState ->
        SignUpScreenContent(
            modifier = modifier,
            uiState = uiState,
            onFirstNameChanged = ::onFirstNameChanged,
            onLastNameChanged = ::onLastNameChanged,
            onEmailChanged = ::onEmailChanged,
            onUsernameChanged = ::onUsernameChanged,
            onPasswordChanged = ::onPasswordChanged,
            onRepeatPasswordChanged = ::onRepeatPasswordChanged,
            onSigUpPressed = ::onSignUp,
            onCancelPressed = onBack,
            onErrorAcceptPressed = ::onErrorAccepted
        )
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpScreenPrev() {
    TvNexaTheme {
        SignUpScreen(
            modifier = Modifier.fillMaxSize(),
            onBack = {}
        )
    }
}
