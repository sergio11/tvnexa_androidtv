package com.dreamsoftware.tvnexa.ui.features.profiles.blocking

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportSearchViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileBlockingChannelsViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    searchChannelsUseCase: SearchChannelsUseCase
): SupportSearchViewModel<ProfileBlockingChannelsUiState, ProfileAdvanceSideEffects>(searchChannelsUseCase) {

    override val currentTerm: String
        get() = uiState.value.term

    override fun onTermUpdated(newTerm: String) {
        updateState { it.copy(term = newTerm) }
    }

    override fun onSearchCompletedSuccessfully(channels: List<SimpleChannelBO>) {
        updateState { it.copy(channels = channels) }
    }

    override fun onGetDefaultState(): ProfileBlockingChannelsUiState = ProfileBlockingChannelsUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }
}

data class ProfileBlockingChannelsUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val profile: ProfileBO? = null,
    val term: String = String.EMPTY,
    val channels: List<SimpleChannelBO> = emptyList(),
): UiState<ProfileBlockingChannelsUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): ProfileBlockingChannelsUiState =
        copy(isLoading = isLoading, error = error)
}


sealed interface ProfileAdvanceSideEffects: SideEffect