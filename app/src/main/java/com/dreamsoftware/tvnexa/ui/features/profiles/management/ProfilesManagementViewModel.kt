package com.dreamsoftware.tvnexa.ui.features.profiles.management

import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilesManagementViewModel @Inject constructor():
    SupportViewModel<ProfilesManagementUiState, ProfilesManagementSideEffects>() {

    override fun onGetDefaultState(): ProfilesManagementUiState = ProfilesManagementUiState()

}

data class ProfilesManagementUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null
): UiState<ProfilesManagementUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): ProfilesManagementUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface ProfilesManagementSideEffects: SideEffect