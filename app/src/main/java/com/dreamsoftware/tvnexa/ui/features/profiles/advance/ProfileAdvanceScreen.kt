package com.dreamsoftware.tvnexa.ui.features.profiles.advance

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ProfileAdvanceScreen(
    args: ProfileAdvanceScreenArgs,
    viewModel: ProfileAdvanceViewModel = hiltViewModel(),
    onGoToDeleteProfile: (String) -> Unit,
    onGoToBlockingChannels: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { ProfileAdvanceUiState() },
    ) { uiState ->
        ProfileAdvanceScreenContent(
            uiState = uiState,
            onConfirmPressed = onBackPressed,
            onDeleteProfilePressed = {
                onGoToDeleteProfile(args.profileId)
            },
            onBlockingChannelsPressed = {
                onGoToBlockingChannels(args.profileId)
            },
            onNewTabSelected = ::onNewTabSelected
        )
    }
}
