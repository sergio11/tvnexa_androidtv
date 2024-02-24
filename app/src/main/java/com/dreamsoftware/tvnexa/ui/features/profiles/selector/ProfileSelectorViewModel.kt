package com.dreamsoftware.tvnexa.ui.features.profiles.selector

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
        executeUseCase(
            useCase = getProfilesUseCase,
            onSuccess = ::onLoadProfileSuccessfully
        )
    }

    fun onProfileSelected(profileBO: ProfileBO) {
        val isProfileLocked = profileBO.enableNSFW
        updateState {
            it.copy(profileSelected = profileBO)
        }
        if(isProfileLocked) {
            onProfileLocked(profileBO.uuid)
        } else {
            selectProfile(profileBO)
        }
    }

    private fun onLoadProfileSuccessfully(profiles: List<ProfileBO>) {
        updateState {
            it.copy(profiles = profiles)
        }
    }

    private fun onProfileLocked(profileId: String) {
        launchSideEffect(ProfileSelectorSideEffects.ProfileLocked(profileId))
    }

    private fun onProfileSelected() {
        launchSideEffect(ProfileSelectorSideEffects.ProfileSelected)
    }

    private fun selectProfile(profileBO: ProfileBO) {
        executeUseCaseWithParams(
            useCase = selectProfileUseCase,
            params = SelectProfileUseCase.Params(profileBO),
            onSuccess = {
                onProfileSelected()
            }
        )
    }
}

data class ProfileSelectorUiState(
    override var isLoading: Boolean = false,
    override var error: String? = null,
    val profiles: List<ProfileBO> = emptyList(),
    val profileSelected: ProfileBO? = null
): UiState<ProfileSelectorUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): ProfileSelectorUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface ProfileSelectorSideEffects: SideEffect {
    data object ProfileSelected: ProfileSelectorSideEffects
    data class ProfileLocked(val profileId: String): ProfileSelectorSideEffects
}