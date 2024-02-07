package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfilesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SelectProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyPinUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
    private val verifyPinUseCase: VerifyPinUseCase
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

    fun onVerifyPin(profileBO: ProfileBO, pin: String) {
        onLoading()
        verifyPinUseCase.invoke(
            scope = viewModelScope,
            params = VerifyPinUseCase.Params(profileId = profileBO.uuid, pin = pin.toInt()),
            onSuccess = { selectProfile(profileBO) },
            onError = ::onErrorOccurred
        )
    }

    fun onCancelProfileSelection() {
        updateState {
            it.copy(showUnlockProfileDialog = false)
        }
    }

    fun onProfileSelected(profileBO: ProfileBO) {
        val isProfileLocked = profileBO.isAdmin
        updateState {
            it.copy(
                profileSelected = profileBO,
                showUnlockProfileDialog = isProfileLocked
            )
        }
        if(!isProfileLocked) {
            selectProfile(profileBO)
        }
    }

    private fun onLoading() {
        updateState { it.copy(
            isLoading = true,
            showUnlockProfileDialog = false
        ) }
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
    val profileSelected: ProfileBO? = null,
    val showUnlockProfileDialog: Boolean = false
): UiState

sealed interface ProfileSelectorSideEffects: SideEffect {

    data object ProfileSelected: ProfileSelectorSideEffects
}