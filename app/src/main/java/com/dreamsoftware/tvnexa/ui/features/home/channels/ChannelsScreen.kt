package com.dreamsoftware.tvnexa.ui.features.home.channels

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
            onNewCategorySelected = ::onNewCategorySelected,
            onChannelPressed = {
                onGoToChannelDetail(it.channelId)
            }
        )
    }
}



