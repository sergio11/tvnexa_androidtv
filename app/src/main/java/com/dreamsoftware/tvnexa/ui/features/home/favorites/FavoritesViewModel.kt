package com.dreamsoftware.tvnexa.ui.features.home.favorites

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetFavoriteChannelsUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteChannelsUseCase: GetFavoriteChannelsUseCase
): SupportViewModel<FavoritesUiState, FavoritesSideEffects>() {
    override fun onGetDefaultState(): FavoritesUiState = FavoritesUiState()

    fun load() {
        executeUseCase(
            useCase = getFavoriteChannelsUseCase,
            onSuccess = ::onLoadFavoriteChannelsSuccessfully,
        )
    }

    private fun onLoadFavoriteChannelsSuccessfully(channels: List<SimpleChannelBO>) {
        updateState {
            it.copy(channels = channels)
        }
    }
}

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channels: List<SimpleChannelBO> = emptyList()
): UiState<FavoritesUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): FavoritesUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface FavoritesSideEffects: SideEffect