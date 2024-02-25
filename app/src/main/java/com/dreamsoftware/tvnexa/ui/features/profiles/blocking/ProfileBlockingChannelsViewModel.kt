package com.dreamsoftware.tvnexa.ui.features.profiles.blocking

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.BlockChannelUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.UnblockChannelUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportSearchViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileBlockingChannelsViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    searchChannelsUseCase: SearchChannelsUseCase,
    private val blockChannelUseCase: BlockChannelUseCase,
    private val unblockChannelUseCase: UnblockChannelUseCase
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

    fun onBlockChannel(channel: SimpleChannelBO) {
        executeUseCaseWithParams(
            useCase = blockChannelUseCase,
            params = BlockChannelUseCase.Params(channelId = channel.channelId),
            onSuccess = {
                onBlockChannelCompleted(channelId = channel.channelId)
            }
        )
    }

    fun onUnblockChannel(channel: SimpleChannelBO) {
        executeUseCaseWithParams(
            useCase = unblockChannelUseCase,
            params = UnblockChannelUseCase.Params(channelId = channel.channelId),
            onSuccess = {
                onUnblockChannelCompleted(channelId = channel.channelId)
            }
        )
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }

    private fun onBlockChannelCompleted(channelId: String) {

    }

    private fun onUnblockChannelCompleted(channelId: String) {

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