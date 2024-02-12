package com.dreamsoftware.tvnexa.ui.features.home.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onGoToChannelDetail: (String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SearchUiState() }
    ) { uiState ->
        SearchScreenContent(
            uiState = uiState,
            onKeyPressed = ::onKeyPressed,
            onSearchPressed = ::onSearchPressed,
            onClearPressed = ::onClearPressed,
            onBackSpacePressed = ::onBackSpacePressed,
            onSpaceBarPressed = ::onSpaceBarPressed,
            onChannelPressed = { onGoToChannelDetail(it.channelId) }
        )
    }
}


