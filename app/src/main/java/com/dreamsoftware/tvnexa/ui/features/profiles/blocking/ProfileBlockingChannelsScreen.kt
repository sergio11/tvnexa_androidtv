package com.dreamsoftware.tvnexa.ui.features.profiles.blocking

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ProfileBlockingChannelsScreen(
    args: ProfileBlockingChannelsScreenArgs,
    viewModel: ProfileBlockingChannelsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { load(args.profileId) },
        onBackPressed = onBackPressed,
        onInitialUiState = { ProfileBlockingChannelsUiState() },
    ) { uiState ->
        ProfileBlockingChannelsContent(
            uiState = uiState,
            onConfirmPressed = onBackPressed,
            onKeyPressed = ::onKeyPressed,
            onSearchPressed = ::onSearchPressed,
            onClearPressed = ::onClearPressed,
            onBackSpacePressed = ::onBackSpacePressed,
            onSpaceBarPressed = ::onSpaceBarPressed,
            onChannelPressed = {}
        )
    }
}
