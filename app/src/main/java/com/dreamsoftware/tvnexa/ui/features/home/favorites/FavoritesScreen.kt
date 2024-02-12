package com.dreamsoftware.tvnexa.ui.features.home.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onGoToChannelDetail: (String) -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { FavoritesUiState() },
        onInit = { load() }
    ) { uiState ->
       FavoritesScreenContent(
           uiState = uiState,
           onChannelPressed = {
               onGoToChannelDetail(it.channelId)
           }
       )
    }
}




