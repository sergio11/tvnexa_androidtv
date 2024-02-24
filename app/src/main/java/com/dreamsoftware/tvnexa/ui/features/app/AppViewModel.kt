package com.dreamsoftware.tvnexa.ui.features.app

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.utils.AppEvent
import com.dreamsoftware.tvnexa.utils.AppEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appEventBus: AppEventBus
): SupportViewModel<AppUiState, AppSideEffects>() {

    init {
        observeEvents()
    }

    override fun onGetDefaultState(): AppUiState = AppUiState()

    private fun observeEvents() {
        viewModelScope.launch {
            appEventBus.events.collect { event ->
                when(event) {
                    is AppEvent.ComeFromStandby -> launchSideEffect(AppSideEffects.ComeFromStandby)
                    is AppEvent.SignOff -> launchSideEffect(AppSideEffects.NoSessionActive)
                    is AppEvent.NetworkConnectivityStateChanged ->
                        onNetworkConnectivityChanged(event.newState)
                    AppEvent.GoToStandby -> {}
                }
            }
        }
    }

    private fun onNetworkConnectivityChanged(newState: Boolean) {
        updateState {
            it.copy(hasNetworkConnectivity = newState)
        }
    }
}

data class AppUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val hasNetworkConnectivity: Boolean = true
): UiState<AppUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): AppUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface AppSideEffects: SideEffect {
    data object ComeFromStandby: AppSideEffects
    data object NoSessionActive: AppSideEffects
}