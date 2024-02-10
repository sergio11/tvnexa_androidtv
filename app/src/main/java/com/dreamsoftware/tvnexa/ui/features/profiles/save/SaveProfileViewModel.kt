package com.dreamsoftware.tvnexa.ui.features.profiles.save

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.ProfileTypeEnum
import com.dreamsoftware.tvnexa.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.CreateProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.UpdateProfileUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveProfileViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
): SupportViewModel<SaveProfileUiState, SaveProfileSideEffects>() {

    override fun onGetDefaultState(): SaveProfileUiState = SaveProfileUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onProfileTypeChanged(newProfileType: ProfileTypeEnum) {
        updateState {
            it.copy(profileType = newProfileType)
        }
    }

    fun onAliasChanged(newAlias: String) {
        updateState {
            it.copy(alias = newAlias)
        }
    }

    fun onSecurePinChanged(newSecurePin: String) {
        updateState {
            it.copy(securePin = newSecurePin)
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(
                isEditMode = true,
                alias = profileBO.alias,
                profileType = profileBO.type
            )
        }
    }
}

data class SaveProfileUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val isEditMode: Boolean = false,
    val alias: String = String.EMPTY,
    val securePin: String = String.EMPTY,
    val profileType: ProfileTypeEnum? = null
): UiState<SaveProfileUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SaveProfileUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SaveProfileSideEffects: SideEffect