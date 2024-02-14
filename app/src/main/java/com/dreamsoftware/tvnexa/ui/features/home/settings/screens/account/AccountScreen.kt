package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.account

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel()
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { AccountUiState() },
        onInit = { load() }
    ) { uiState ->
        AccountScreenContent(
            uiState = uiState,
            onSignOffPressed = ::signOff,
            onConfirmSignOff = ::onConfirmSignOff,
            onCancelSignOff = ::onCancelSignOff
        )
    }
}