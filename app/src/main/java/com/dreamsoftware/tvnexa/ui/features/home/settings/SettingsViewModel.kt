package com.dreamsoftware.tvnexa.ui.features.home.settings

import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.features.home.settings.data.SettingsMenuData
import com.dreamsoftware.tvnexa.ui.features.home.settings.model.SettingsItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): SupportViewModel<SettingsUiState, SettingsSideEffects>() {
    override fun onGetDefaultState(): SettingsUiState = SettingsUiState(
        mainMenuItems = SettingsMenuData.menu
    )
}

data class SettingsUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val mainMenuItems: List<SettingsItemMenu> = emptyList(),
): UiState<SettingsUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SettingsUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SettingsSideEffects: SideEffect