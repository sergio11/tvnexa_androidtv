package com.dreamsoftware.tvnexa.ui.features.home.search

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.ui.extensions.SPACE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchChannelsUseCase: SearchChannelsUseCase
): SupportViewModel<SearchUiState, SearchSideEffects>() {
    companion object {
        private const val SEARCH_DELAY_MILLIS: Long = 3000
    }

    private var searchJob: Job? = null

    override fun onGetDefaultState(): SearchUiState = SearchUiState()

    fun onKeyPressed(key: String) {
        updateState { it.copy(term = it.term.plus(key)) }
        launchSearchAfterDelay()
    }

    fun onClearPressed() {
        updateState { it.copy(term = String.EMPTY) }
    }

    fun onBackSpacePressed() {
        updateState { it.copy(term = it.term.dropLast(1)) }
        launchSearchAfterDelay()
    }

    fun onSpaceBarPressed() {
        updateState { it.copy(term = it.term.plus(Char.SPACE)) }
        launchSearchAfterDelay()
    }

    fun onSearchPressed() {
        launchSearch()
    }

    private fun launchSearchAfterDelay() {
        searchJob?.cancel()
        viewModelScope.launch {
            delay(SEARCH_DELAY_MILLIS)
            launchSearch()
        }.also {
            searchJob = it
        }
    }

    private fun launchSearch() {
        val currentTerm = uiState.value.term
        if (currentTerm.isNotBlank()) {
            onSearch()
        }
    }

    private fun onSearch() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = searchChannelsUseCase,
                params = SearchChannelsUseCase.Params(term = term),
                onSuccess = ::onSearchCompletedSuccessfully,
            )
        }
    }

    private fun onSearchCompletedSuccessfully(channels: List<SimpleChannelBO>) {
        updateState {
            it.copy(channels = channels)
        }
    }
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