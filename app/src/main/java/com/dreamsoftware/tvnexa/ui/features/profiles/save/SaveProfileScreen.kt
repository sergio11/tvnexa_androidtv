package com.dreamsoftware.tvnexa.ui.features.profiles.save

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun SaveProfileScreen(
    args: SaveProfileScreenArgs? = null,
    viewModel: SaveProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = {  },
        onBackPressed = onBackPressed,
        onInitialUiState = { SaveProfileUiState() },
    ) { uiState ->
        SaveProfileScreenContent(
            uiState = uiState,
            onAliasChanged = {},
            onPinChanged = {},
            onSaveProfilePressed = {},
            onCancelPressed = onBackPressed
        )
    }
}
