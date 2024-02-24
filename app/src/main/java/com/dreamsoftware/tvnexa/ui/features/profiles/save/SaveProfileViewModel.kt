package com.dreamsoftware.tvnexa.ui.features.profiles.save

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.CreateProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.UpdateProfileUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.utils.combinedLet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveProfileViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
): SupportViewModel<SaveProfileUiState, SaveProfileSideEffects>() {

    override fun onGetDefaultState(): SaveProfileUiState = SaveProfileUiState()

    private lateinit var profileId: String

    fun load(profileId: String) {
        this.profileId = profileId
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onSaveProfile() {
        with(uiState.value) {
            if(isEditMode) {
                onUpdateProfile()
            } else {
                onCreateProfile()
            }
        }
    }

    fun onAvatarTypeChanged(newAvatarType: AvatarTypeEnum) {
        updateState {
            it.copy(avatarType = newAvatarType)
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

    fun onIsNsfwChanged(isNsfw: Boolean) {
        updateState {
            it.copy(enableNSFW = isNsfw)
        }
    }

    private fun onUpdateProfile() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = updateProfileUseCase,
                params = UpdateProfileUseCase.Params(
                    profileId = profileId,
                    alias = alias,
                    enableNSFW = enableNSFW,
                    avatarType  = avatarType?.name
                ),
                onSuccess = {
                    onSaveProfileSuccessfully()
                }
            )
        }
    }

    private fun onCreateProfile() {
        with(uiState.value) {
            combinedLet(avatarType, securePin.toIntOrNull()) { type, pin ->
                executeUseCaseWithParams(
                    useCase = createProfileUseCase,
                    params = CreateProfileUseCase.Params(
                        alias = alias,
                        pin = pin,
                        enableNSFW = enableNSFW,
                        avatarType = type
                    ),
                    onSuccess = {
                        onSaveProfileSuccessfully()
                    }
                )
            }
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(
                isEditMode = true,
                alias = profileBO.alias,
                avatarType = profileBO.avatarType,
                enableNSFW = profileBO.enableNSFW
            )
        }
    }

    private fun onSaveProfileSuccessfully() {
        launchSideEffect(SaveProfileSideEffects.SaveProfileSuccessfully)
    }
}

data class SaveProfileUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val isEditMode: Boolean = false,
    val alias: String = String.EMPTY,
    val securePin: String = String.EMPTY,
    val enableNSFW: Boolean = false,
    val avatarType: AvatarTypeEnum? = null
): UiState<SaveProfileUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SaveProfileUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SaveProfileSideEffects: SideEffect {
    data object SaveProfileSuccessfully: SaveProfileSideEffects
}