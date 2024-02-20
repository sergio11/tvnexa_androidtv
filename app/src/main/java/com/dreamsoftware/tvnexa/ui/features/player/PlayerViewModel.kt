package com.dreamsoftware.tvnexa.ui.features.player

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getChannelDetailUseCase: GetChannelDetailUseCase
): SupportViewModel<PlayerUiState, PlayerSideEffects>() {
    override fun onGetDefaultState(): PlayerUiState = PlayerUiState()

    fun loadDetail(channelId: String) {
        executeUseCaseWithParams(
            useCase = getChannelDetailUseCase,
            params = GetChannelDetailUseCase.Params(channelId = channelId),
            onSuccess = ::onChannelDetailLoadSuccessfully
        )
    }

    private fun onChannelDetailLoadSuccessfully(channelDetail: ChannelDetailBO) {
        updateState {
            it.copy(channelDetail = channelDetail)
        }
    }
}

data class PlayerUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channelDetail: ChannelDetailBO? = null
): UiState<PlayerUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): PlayerUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface PlayerSideEffects: SideEffect