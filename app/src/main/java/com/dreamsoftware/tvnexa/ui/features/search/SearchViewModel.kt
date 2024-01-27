package com.dreamsoftware.tvnexa.ui.features.search

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): SupportViewModel<SearchUiState, SearchSideEffects>() {
    override fun onGetDefaultState(): SearchUiState = SearchUiState()
}

data class SearchUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val channels: List<SimpleChannelBO> = emptyList()
): UiState

sealed interface SearchSideEffects: SideEffect