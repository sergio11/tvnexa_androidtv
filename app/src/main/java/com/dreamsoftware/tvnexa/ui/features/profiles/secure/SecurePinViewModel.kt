package com.dreamsoftware.tvnexa.ui.features.profiles.secure

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.SelectProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyPinUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurePinViewModel @Inject constructor(
    private val verifyPinUseCase: VerifyPinUseCase,
    private val selectProfileUseCase: SelectProfileUseCase
): SupportViewModel<SecurePinUiState, SecurePinSideEffects>() {

    private lateinit var profileId: String

    override fun onGetDefaultState(): SecurePinUiState = SecurePinUiState()

    fun load(profileId: String) {
        this.profileId = profileId
    }

    fun onUnlockPinChanged(unlockPin: String) {
        updateState {
            it.copy(unlockPin = unlockPin)
        }
    }

    fun onVerifyPin() {
        onLoading()
        verifyPinUseCase.invoke(
            scope = viewModelScope,
            params = VerifyPinUseCase.Params(profileId = profileId, pin = uiState.value.unlockPin.toInt()),
            onSuccess = {  },
            onError = ::onErrorOccurred
        )
    }

    private fun onProfileSelected() {
        launchSideEffect(SecurePinSideEffects.ProfileUnlockedSuccessfully)
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

    private fun onLoading() {
        updateState {
            it.copy(isLoading = true)
        }
    }

    private fun onErrorOccurred(ex: Exception) {
        ex.printStackTrace()
    }
}

data class SecurePinUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val unlockPin: String = String.EMPTY
): UiState

sealed interface SecurePinSideEffects: SideEffect {
    data object ProfileUnlockedSuccessfully: SecurePinSideEffects
}