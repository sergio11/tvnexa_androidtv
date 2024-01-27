package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.ConsumeSideEffects
import com.dreamsoftware.tvnexa.ui.components.produceUiState

@Composable
fun ProfileSelectorScreen(
    viewModel: ProfileSelectorViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit
) {
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel
        ) {
            if(it is ProfileSelectorSideEffects.ProfileSelected) {
                onProfileSelected()
            }
        }

        val uiState by produceUiState(
            initialState = ProfileSelectorUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )

        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {
            loadProfiles()
        }

        ProfileSelectorContent(
            uiState = uiState,
            onProfileSelected = ::onProfileSelected
        )
    }
}
