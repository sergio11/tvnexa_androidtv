package com.dreamsoftware.tvnexa.ui.features.profiles.secure

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SelectProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyPinUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.utils.combinedLet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecurePinViewModel @Inject constructor(
    private val verifyPinUseCase: VerifyPinUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): SupportViewModel<SecurePinUiState, SecurePinSideEffects>() {


    override fun onGetDefaultState(): SecurePinUiState = SecurePinUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onVerifyPin() {
        with(uiState.value) {
            combinedLet(profileLocked, unlockPin.toIntOrNull()) { profile, pin ->
                executeUseCaseWithParams(
                    useCase = verifyPinUseCase,
                    params = VerifyPinUseCase.Params(profileId = profile.uuid, pin = pin),
                    onSuccess = {
                        onVerifyPinSuccessfully(profile)
                    }
                )
            }
        }
    }

    fun onUnlockPinChanged(unlockPin: String) {
        updateState {
            it.copy(unlockPin = unlockPin)
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profileLocked = profileBO)
        }
    }

    private fun onVerifyPinSuccessfully(profile: ProfileBO) {
        executeUseCaseWithParams(
            useCase = selectProfileUseCase,
            params = SelectProfileUseCase.Params(profile),
            onSuccess = {
                onProfileSelected()
            }
        )
    }

    private fun onProfileSelected() {
        launchSideEffect(SecurePinSideEffects.ProfileUnlockedSuccessfully)
    }
}

data class SecurePinUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val unlockPin: String = String.EMPTY,
    val profileLocked: ProfileBO? = null
): UiState<SecurePinUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SecurePinUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SecurePinSideEffects: SideEffect {
    data object ProfileUnlockedSuccessfully: SecurePinSideEffects
}