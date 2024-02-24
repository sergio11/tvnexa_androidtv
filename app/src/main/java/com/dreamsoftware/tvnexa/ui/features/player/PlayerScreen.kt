package com.dreamsoftware.tvnexa.ui.features.player

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel(),
    args: PlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { PlayerUiState() },
        onInit = { loadDetail(args.channelId) },
        onBackPressed = onBackPressed
    ) { uiState ->
        PlayerScreenContent(
            uiState = uiState,
            onFavoriteStateChanged = { isFavorite ->
                if(isFavorite) {
                    saveAsFavorite()
                } else {
                    removeFromFavorites()
                }
            }
        )
    }
}