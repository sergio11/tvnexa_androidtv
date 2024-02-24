package com.dreamsoftware.tvnexa.ui.features.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun DetailsScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    args: DetailScreenArgs,
    onBackPressed: () -> Unit,
    onPlayChannelPressed: (channelId: String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { DetailUiState() },
        onBackPressed = onBackPressed,
        onInit = { loadDetail(args.channelId) }
    ) { uiState ->
        DetailsScreenContent(
            uiState = uiState,
            onFavoriteStateChanged = { isFavorite ->
                 if(isFavorite) {
                     saveAsFavorite()
                 } else {
                     removeFromFavorites()
                 }
            },
            onPlayChannelPressed = onPlayChannelPressed
        )
    }
}
