package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.ConsumeSideEffects
import com.dreamsoftware.tvnexa.ui.components.produceUiState

@Composable
fun ChannelsScreen(
    viewModel: ChannelsViewModel = hiltViewModel(),
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit,
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel
        ) {

        }

        val uiState by produceUiState(
            initialState = ChannelsUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {
            loadData()
        }

        ChannelScreenContent(
            uiState = uiState
        )
    }
}



