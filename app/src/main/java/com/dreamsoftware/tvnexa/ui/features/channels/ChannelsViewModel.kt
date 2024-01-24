package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCategoriesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCountriesUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getChannelsUseCase: GetChannelsUseCase
): SupportViewModel<ChannelsUiState, ChannelsSideEffects>() {

    override fun onGetDefaultState(): ChannelsUiState = ChannelsUiState()

    fun loadData() {
        onLoading()
        onLoadData()
    }

    private fun onLoadData() = viewModelScope.launch {
        runCatching {
            val getCountriesDeferred = async { getCountries() }
            val getCategoriesDeferred = async { getCategories() }
            val countries = getCountriesDeferred.await()
            val categories = getCategoriesDeferred.await()
            val countrySelected = countries.firstOrNull()
            val categorySelected = categories.firstOrNull()
            updateState {
                it.copy(
                    isLoading = false,
                    countries = countries,
                    categories = categories,
                    countrySelected = countrySelected,
                    categorySelected = categorySelected
                )
            }
            onLoadChannels(countrySelected, categorySelected)
        }.onFailure(::onErrorOccurred)
    }

    private fun onLoadChannels(countrySelected: CountryBO?, categorySelected: CategoryBO?) {
        getChannelsUseCase.invoke(
            scope = viewModelScope,
            params = GetChannelsUseCase.Params(
                category = categorySelected?.id,
                country = countrySelected?.code,
                offset = 1,
                limit = 100
            ),
            onSuccess = ::onLoadChannelsSuccessfully,
            onError = ::onErrorOccurred
        )
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                error = null
            )
        }
    }

    private fun onLoadChannelsSuccessfully(channels: List<SimpleChannelBO>) {
        updateState {
            it.copy(
                channels = channels
            )
        }
    }

    private fun onErrorOccurred(ex: Throwable) {
        ex.printStackTrace()
        updateState {
            it.copy(isLoading = false)
        }
    }

    private suspend fun getCountries() = runCatching {
        getCountriesUseCase.invoke(scope = viewModelScope)
    }.getOrDefault(emptyList())

    private suspend fun getCategories() = runCatching {
        getCategoriesUseCase.invoke(scope = viewModelScope)
    }.getOrDefault(emptyList())
}

data class ChannelsUiState(
    override val isLoading: Boolean = false,
    override val error: Exception? = null,
    val countries: List<CountryBO> = emptyList(),
    val categories: List<CategoryBO> = emptyList(),
    val channels: List<SimpleChannelBO> = emptyList(),
    val countrySelected: CountryBO? = null,
    val categorySelected: CategoryBO? = null
): UiState

sealed interface ChannelsSideEffects: SideEffect