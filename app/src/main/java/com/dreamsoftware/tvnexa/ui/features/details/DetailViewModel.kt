package com.dreamsoftware.tvnexa.ui.features.details

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getChannelDetailUseCase: GetChannelDetailUseCase
): SupportViewModel<DetailUiState, DetailSideEffects>() {
    override fun onGetDefaultState(): DetailUiState = DetailUiState()

    fun loadDetail(channelId: String) {
        onLoading()
        getChannelDetailUseCase.invoke(
            scope = viewModelScope,
            params = GetChannelDetailUseCase.Params(channelId = channelId),
            onSuccess = ::onChannelDetailLoadSuccessfully,
            onError = ::onErrorOccurred
        )
    }

    private fun onChannelDetailLoadSuccessfully(channelDetail: ChannelDetailBO) {
        updateState {
            it.copy(
                isLoading = false,
                channelDetail = channelDetail
            )
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                error = null
            )
        }
    }

    private fun onErrorOccurred(ex: Throwable) {
        ex.printStackTrace()
        updateState {
            it.copy(isLoading = false)
        }
    }
}

data class DetailUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channelDetail: ChannelDetailBO? = null
): UiState

sealed interface DetailSideEffects: SideEffect