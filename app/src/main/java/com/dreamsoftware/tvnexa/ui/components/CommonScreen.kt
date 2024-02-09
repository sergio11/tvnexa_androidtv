package com.dreamsoftware.tvnexa.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState

@Composable
fun <UI: UiState<UI>, SE: SideEffect, VM: SupportViewModel<UI, SE>> CommonScreen(
    viewModel: VM,
    onInitialUiState: () -> UI,
    onInit: VM.() -> Unit = {},
    onBackPressed: () -> Unit = {},
    onSideEffect: VM.(SE) -> Unit = {},
    content: @Composable VM.(uiState: UI) -> Unit
) {
    BackHandler(onBack = onBackPressed)
    with(viewModel) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        ConsumeSideEffects(
            lifecycle = lifecycle,
            viewModel = viewModel,
            onSideEffectFired = {
                onSideEffect(it)
            }
        )
        val uiState by produceUiState(
            initialState = onInitialUiState(),
            lifecycle = lifecycle,
            viewModel = viewModel
        )
        LaunchedEffect(key1 = lifecycle, key2 = viewModel) {
            onInit()
        }
        content(uiState)
    }
}