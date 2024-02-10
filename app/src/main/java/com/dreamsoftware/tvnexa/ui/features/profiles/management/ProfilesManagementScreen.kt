package com.dreamsoftware.tvnexa.ui.features.profiles.management

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ProfilesManagementScreen(
    viewModel: ProfilesManagementViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = {  },
        onBackPressed = onBackPressed,
        onInitialUiState = { ProfilesManagementUiState() },
    ) { uiState ->
        ProfilesManagementScreenContent(
            uiState = uiState,
            onCompletePressed = onBackPressed
        )
    }
}
