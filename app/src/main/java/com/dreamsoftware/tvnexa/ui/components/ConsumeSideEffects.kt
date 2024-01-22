package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel

@Composable
fun <SE: SideEffect, VM: SupportViewModel<*, SE>>ConsumeSideEffects(
    viewModel: VM,
    lifecycle: Lifecycle,
    onSideEffectFired: (SE) -> Unit
) {
    LaunchedEffect(viewModel, lifecycle) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { event ->
                onSideEffectFired(event)
            }
        }
    }
}