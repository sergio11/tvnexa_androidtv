package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ChannelsScreen(
    viewModel: ChannelsViewModel = hiltViewModel(),
    onGoToChannelDetail: (channelId: String) -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { ChannelsUiState() },
        onInit = { loadData() }
    ) { uiState ->
        ChannelScreenContent(
            uiState = uiState,
            onNewCountrySelected = ::onNewCountrySelected,
            onChannelFocused = ::onNewChannelFocused,
            onChannelPressed = {
                onGoToChannelDetail(it.channelId)
            }
        )
    }
}



