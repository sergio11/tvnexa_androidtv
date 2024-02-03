package com.dreamsoftware.tvnexa.ui.features.player

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.produceUiState

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel(),
    args: PlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    with(viewModel) {
        BackHandler(onBack = onBackPressed)
        val lifecycle = LocalLifecycleOwner.current.lifecycle

        val uiState by produceUiState(
            initialState = PlayerUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {
            loadDetail(args.channelId)
        }

        PlayerScreenContent(
            uiState = uiState
        )
    }
}