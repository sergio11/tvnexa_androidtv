package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.ConsumeSideEffects
import com.dreamsoftware.tvnexa.ui.components.produceUiState
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel
        ) {
            if(it is SignUpSideEffects.RegisteredSuccessfully) {
                onBack()
            }
        }

        val uiState by produceUiState(
            initialState = SignUpUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

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
            onCancelPressed = onBack
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
