package com.dreamsoftware.tvnexa.ui.features.profiles.secure

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun SecurePinScreen(
    args: SecurePinScreenArgs,
    viewModel: SecurePinViewModel = hiltViewModel(),
    onGoToHome: () -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { SecurePinUiState() },
        onSideEffect = {
            if(it is SecurePinSideEffects.ProfileUnlockedSuccessfully) {
                onGoToHome()
            }
        }
    ) { uiState ->
        SecurePinScreenContent(
            uiState = uiState,
            onUnlockPinChanged = ::onUnlockPinChanged,
            onVerifyPressed = ::onVerifyPin,
            onCancelPressed = onBackPressed,
            onErrorAccepted = ::onErrorAccepted
        )
    }
}
