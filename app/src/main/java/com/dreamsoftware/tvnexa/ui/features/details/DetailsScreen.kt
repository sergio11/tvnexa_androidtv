package com.dreamsoftware.tvnexa.ui.features.details

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.produceUiState

@Composable
fun DetailsScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onPlayChannelPressed: () -> Unit
) {
    with(viewModel) {
        BackHandler(onBack = onBackPressed)
        val lifecycle = LocalLifecycleOwner.current.lifecycle

        val uiState by produceUiState(
            initialState = DetailUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {

        }

        DetailsScreenContent(
            uiState = uiState,
            onPlayChannelPressed = onPlayChannelPressed
        )
    }
}
