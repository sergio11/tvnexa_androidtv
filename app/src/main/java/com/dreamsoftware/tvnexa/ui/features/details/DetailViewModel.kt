package com.dreamsoftware.tvnexa.ui.features.details

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.DeleteFavoriteChannelUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SaveFavoriteChannelUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getChannelDetailUseCase: GetChannelDetailUseCase,
    private val saveFavoriteChannelUseCase: SaveFavoriteChannelUseCase,
    private val deleteFavoriteChannelUseCase: DeleteFavoriteChannelUseCase
): SupportViewModel<DetailUiState, DetailSideEffects>() {

    private lateinit var channelId: String

    override fun onGetDefaultState(): DetailUiState = DetailUiState()

    fun loadDetail(channelId: String) {
        this.channelId = channelId
        executeUseCaseWithParams(
            useCase = getChannelDetailUseCase,
            params = GetChannelDetailUseCase.Params(channelId = channelId),
            onSuccess = ::onChannelDetailLoadSuccessfully
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
}

data class DetailUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channelDetail: ChannelDetailBO? = null,
    val isSavedInFavorites: Boolean = false,
): UiState<DetailUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): DetailUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface DetailSideEffects: SideEffect