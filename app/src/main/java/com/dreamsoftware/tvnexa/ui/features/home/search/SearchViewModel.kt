package com.dreamsoftware.tvnexa.ui.features.home.search

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportSearchViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchChannelsUseCase: SearchChannelsUseCase
): SupportSearchViewModel<SearchUiState, SearchSideEffects>(searchChannelsUseCase) {
    override val currentTerm: String
        get() = uiState.value.term

    override fun onTermUpdated(newTerm: String) {
        updateState { it.copy(term = newTerm) }
    }

    override fun onSearchCompletedSuccessfully(channels: List<SimpleChannelBO>) {
        updateState { it.copy(channels = channels) }
    }

    override fun onGetDefaultState(): SearchUiState = SearchUiState()

}

data class SearchUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channels: List<SimpleChannelBO> = emptyList(),
    val term: String = String.EMPTY
): UiState<SearchUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SearchUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SearchSideEffects: SideEffect