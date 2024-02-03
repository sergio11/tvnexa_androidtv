package com.dreamsoftware.tvnexa.ui.features.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.produceUiState

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val uiState by produceUiState(
            initialState = SearchUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )
        SearchScreenContent(
            uiState = uiState,
            onKeyPressed = ::onKeyPressed,
            onSearchPressed = ::onSearchPressed,
            onClearPressed = ::onClearPressed,
            onBackSpacePressed = ::onBackSpacePressed,
            onSpaceBarPressed = ::onSpaceBarPressed
        )
    }
}


