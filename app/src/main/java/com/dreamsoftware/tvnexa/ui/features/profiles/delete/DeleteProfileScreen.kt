package com.dreamsoftware.tvnexa.ui.features.profiles.delete

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun DeleteProfileScreen(
    args: DeleteProfileScreenArgs,
    viewModel: DeleteProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { DeleteProfileUiState() },
    ) { uiState ->
        DeleteProfileScreenContent(
            uiState = uiState,
            onDeletePressed = ::onDeleteProfile,
            onCancelPressed = onBackPressed,
            onErrorAccepted = ::onErrorAccepted
        )
    }
}
