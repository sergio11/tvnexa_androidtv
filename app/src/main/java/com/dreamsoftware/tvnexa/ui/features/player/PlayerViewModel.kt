package com.dreamsoftware.tvnexa.ui.features.player

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.DeleteFavoriteChannelUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SaveFavoriteChannelUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyChannelSavedAsFavoriteUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getChannelDetailUseCase: GetChannelDetailUseCase,
    private val saveFavoriteChannelUseCase: SaveFavoriteChannelUseCase,
    private val deleteFavoriteChannelUseCase: DeleteFavoriteChannelUseCase,
    private val verifyChannelSavedAsFavoriteUseCase: VerifyChannelSavedAsFavoriteUseCase
): SupportViewModel<PlayerUiState, PlayerSideEffects>() {

    private lateinit var channelId: String

    override fun onGetDefaultState(): PlayerUiState = PlayerUiState()

    fun loadDetail(channelId: String) {
        this.channelId = channelId
        executeUseCaseWithParams(
            useCase = getChannelDetailUseCase,
            params = GetChannelDetailUseCase.Params(channelId = channelId),
            onSuccess = ::onChannelDetailLoadSuccessfully
        )
        executeUseCaseWithParams(
            useCase = verifyChannelSavedAsFavoriteUseCase,
            params = VerifyChannelSavedAsFavoriteUseCase.Params(channelId = channelId),
            onSuccess = ::onVerifyChannelSavedAsFavorite
        )
    }

    fun saveAsFavorite() {
        executeUseCaseWithParams(
            useCase = saveFavoriteChannelUseCase,
            params = SaveFavoriteChannelUseCase.Params(channelId = channelId),
            onSuccess = {
                onSaveChannelAsFavorite()
            }
        )
    }

    fun removeFromFavorites() {
        executeUseCaseWithParams(
            useCase = deleteFavoriteChannelUseCase,
            params = DeleteFavoriteChannelUseCase.Params(channelId = channelId),
            onSuccess = {
                onDeleteChannelFromFavorites()
            }
        )
    }

    private fun onChannelDetailLoadSuccessfully(channelDetail: ChannelDetailBO) {
        updateState {
            it.copy(channelDetail = channelDetail)
        }
    }

    private fun onSaveChannelAsFavorite() {
        updateState {
            it.copy(isSavedInFavorites = true)
        }
    }

    private fun onDeleteChannelFromFavorites() {
        updateState {
            it.copy(isSavedInFavorites = false)
        }
    }

    private fun onVerifyChannelSavedAsFavorite(isFavorite: Boolean) {
        updateState {
            it.copy(isSavedInFavorites = isFavorite)
        }
    }
}

data class PlayerUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channelDetail: ChannelDetailBO? = null,
    val isSavedInFavorites: Boolean = false
): UiState<PlayerUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): PlayerUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface PlayerSideEffects: SideEffect