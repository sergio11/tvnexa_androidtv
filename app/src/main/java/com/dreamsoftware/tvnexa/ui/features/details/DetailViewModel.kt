package com.dreamsoftware.tvnexa.ui.features.details

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
}

data class DetailUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channelDetail: ChannelDetailBO? = null
): UiState

sealed interface DetailSideEffects: SideEffect