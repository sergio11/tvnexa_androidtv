package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfilesUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase
): SupportViewModel<ProfileSelectorUiState, ProfileSelectorSideEffects>() {
    override fun onGetDefaultState(): ProfileSelectorUiState = ProfileSelectorUiState()

    fun loadProfiles() {
        onLoading()
        getProfilesUseCase.invoke(
            scope = viewModelScope,
            onSuccess = ::onLoadProfileSuccessfully,
            onError = ::onErrorOccurred
        )
    }

    private fun onLoading() {
        updateState { it.copy(isLoading = true) }
    }

    private fun onIdle() {
        updateState { it.copy(isLoading = false) }
    }

    private fun onLoadProfileSuccessfully(profiles: List<ProfileBO>) {
        updateState {
            it.copy(
                isLoading = false,
                profiles = profiles
            )
        }
    }

    private fun onErrorOccurred(ex: Exception) {
        ex.printStackTrace()
        onIdle()
    }
}

data class ProfileSelectorUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val profiles: List<ProfileBO> = emptyList()
): UiState

sealed interface ProfileSelectorSideEffects: SideEffect