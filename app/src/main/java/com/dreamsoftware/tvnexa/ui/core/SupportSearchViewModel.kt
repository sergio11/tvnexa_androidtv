package com.dreamsoftware.tvnexa.ui.core

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.ui.extensions.SPACE
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class SupportSearchViewModel<STATE : UiState<STATE>, EFFECT : SideEffect> constructor(
    private val searchChannelsUseCase: SearchChannelsUseCase
): SupportViewModel<STATE, EFFECT>() {

    companion object {
        private const val SEARCH_DELAY_MILLIS: Long = 3000
    }

    private var searchJob: Job? = null

    abstract val currentTerm: String

    fun onKeyPressed(key: String) {
        onTermUpdated(newTerm = currentTerm.plus(key))
        launchSearchAfterDelay()
    }

    fun onClearPressed() {
        onTermUpdated(newTerm = String.EMPTY)
    }

    fun onBackSpacePressed() {
        onTermUpdated(newTerm = currentTerm.dropLast(1))
        launchSearchAfterDelay()
    }

    fun onSpaceBarPressed() {
        onTermUpdated(newTerm = currentTerm.plus(Char.SPACE))
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
        if (currentTerm.isNotBlank()) {
            onSearch()
        }
    }

    private fun onSearch() {
        executeUseCaseWithParams(
            useCase = searchChannelsUseCase,
            params = SearchChannelsUseCase.Params(term = currentTerm),
            onSuccess = ::onSearchCompletedSuccessfully,
        )
    }

    abstract fun onTermUpdated(newTerm: String)

    abstract fun onSearchCompletedSuccessfully(channels: List<SimpleChannelBO>)
}