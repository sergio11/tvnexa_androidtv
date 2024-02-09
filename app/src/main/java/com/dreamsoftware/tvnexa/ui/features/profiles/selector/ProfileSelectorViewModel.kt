package com.dreamsoftware.tvnexa.ui.features.profiles.selector

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfilesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SelectProfileUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
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

    fun onProfileSelected(profileBO: ProfileBO) {
        val isProfileLocked = profileBO.isAdmin
        updateState {
            it.copy(profileSelected = profileBO)
        }
        if(isProfileLocked) {
            onProfileLocked(profileBO.uuid)
        } else {
            selectProfile(profileBO)
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(isLoading = true)
        }
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

    private fun onProfileLocked(profileId: String) {
        launchSideEffect(ProfileSelectorSideEffects.ProfileLocked(profileId))
    }

    private fun onProfileSelected() {
        launchSideEffect(ProfileSelectorSideEffects.ProfileSelected)
    }

    private fun onErrorOccurred(ex: Exception) {
        ex.printStackTrace()
        onIdle()
    }

    private fun selectProfile(profileBO: ProfileBO) {
        selectProfileUseCase.invoke(
            scope = viewModelScope,
            params = SelectProfileUseCase.Params(profileBO),
            onSuccess = {
                onProfileSelected()
            },
            onError = ::onErrorOccurred
        )
    }
}

data class ProfileSelectorUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val profiles: List<ProfileBO> = emptyList(),
    val profileSelected: ProfileBO? = null
): UiState

sealed interface ProfileSelectorSideEffects: SideEffect {
    data object ProfileSelected: ProfileSelectorSideEffects
    data class ProfileLocked(val profileId: String): ProfileSelectorSideEffects
}