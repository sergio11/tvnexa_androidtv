package com.dreamsoftware.tvnexa.ui.features.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.dreamsoftware.tvnexa.ui.extensions.openSystemSettings

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
        val context = LocalContext.current
        PlayerScreenContent(
            uiState = uiState,
            onFavoriteStateChanged = { isFavorite ->
                if(isFavorite) {
                    saveAsFavorite()
                } else {
                    removeFromFavorites()
                }
            },
            onOpenSettingsPressed = {
                context.openSystemSettings()
            }
        )
    }
}