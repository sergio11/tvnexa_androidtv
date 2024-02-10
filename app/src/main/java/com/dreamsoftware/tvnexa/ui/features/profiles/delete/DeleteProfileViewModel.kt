package com.dreamsoftware.tvnexa.ui.features.profiles.delete

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.DeleteProfileUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteProfileViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
): SupportViewModel<DeleteProfileUiState, DeleteProfileSideEffects>() {

    override fun onGetDefaultState(): DeleteProfileUiState = DeleteProfileUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onDeleteProfile() {
        with(uiState.value) {
            profile?.let {
                executeUseCaseWithParams(
                    useCase = deleteProfileUseCase,
                    params = DeleteProfileUseCase.Params(profileId = it.uuid),
                    onSuccess = {
                        onProfileDeleted()
                    }
                )
            }
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }

    private fun onProfileDeleted() {
        launchSideEffect(DeleteProfileSideEffects.ProfileDeleteSuccessfully)
    }
}

data class DeleteProfileUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val profile: ProfileBO? = null
): UiState<DeleteProfileUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): DeleteProfileUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface DeleteProfileSideEffects: SideEffect {
    data object ProfileDeleteSuccessfully: DeleteProfileSideEffects
}