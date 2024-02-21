package com.dreamsoftware.tvnexa.ui.features.profiles.save

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun SaveProfileScreen(
    args: SaveProfileScreenArgs? = null,
    viewModel: SaveProfileViewModel = hiltViewModel(),
    onGoToAdvanceConfiguration: (String) -> Unit = {},
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { args?.profileId?.let(::load) },
        onBackPressed = onBackPressed,
        onInitialUiState = { SaveProfileUiState() },
        onSideEffect = {
            if(it is SaveProfileSideEffects.SaveProfileSuccessfully) {
                onBackPressed()
            }
        }
    ) { uiState ->
        SaveProfileScreenContent(
            uiState = uiState,
            onAliasChanged = ::onAliasChanged,
            onPinChanged = ::onSecurePinChanged,
            onSaveProfilePressed = ::onSaveProfile,
            onProfileTypeChanged = ::onProfileTypeChanged,
            onNsfwChanged = ::onIsNsfwChanged,
            onAdvanceConfigurationPressed = {
                args?.profileId?.let(onGoToAdvanceConfiguration)
            },
            onCancelPressed = onBackPressed
        )
    }
}
