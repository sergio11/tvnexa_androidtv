package com.dreamsoftware.tvnexa.ui.features.home.channels

import android.util.Log
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
import kotlinx.coroutines.Dispatchers
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
        onLoadData()
    }

    fun onNewCountrySelected(newCountryBO: CountryBO) {
        updateState {
            it.copy(
                channelFocused = null,
                countrySelected = newCountryBO
            )
        }
        onLoadChannels()
    }

    fun onNewCategorySelected(newCategoryBO: CategoryBO) {
        updateState {
            it.copy(
                categorySelected = newCategoryBO
            )
        }
        onLoadChannels()
    }

    fun onNewChannelFocused(newChannelFocused: SimpleChannelBO) {
        updateState { it.copy(channelFocused = newChannelFocused) }
    }

    private fun onLoadData() = viewModelScope.launch {
        val getCountriesDeferred = async(Dispatchers.IO) { getCountries() }
        val getCategoriesDeferred = async(Dispatchers.IO) { getCategories() }
        val countries = getCountriesDeferred.await()
        val categories = getCategoriesDeferred.await()
        updateState {
            it.copy(
                countries = countries,
                categories = categories,
                countrySelected = it.countrySelected ?: countries.firstOrNull()
            )
        }
        onLoadChannels()
    }

    private fun onLoadChannels() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = getChannelsUseCase,
                params = GetChannelsUseCase.Params(
                    category = categorySelected?.id,
                    country = countrySelected?.code,
                    offset = 1,
                    limit = 100
                ),
                onSuccess = ::onLoadChannelsSuccessfully,
            )
        }
    }

    private fun onLoadChannelsSuccessfully(channels: List<SimpleChannelBO>) {
        Log.d("CHANNELS_LIST", "onLoadChannelsSuccessfully channels: ${channels.size} CALLED!")
        updateState {
            it.copy(
                channels = channels,
                channelFocused = it.channelFocused ?: channels.firstOrNull()
            )
        }
    }

    private suspend fun getCountries(): List<CountryBO> =
        executeUseCase(
            useCase = getCountriesUseCase,
            onGetDefaultValue = { emptyList() }
        )

    private suspend fun getCategories() = executeUseCase(
        useCase = getCategoriesUseCase,
        onGetDefaultValue = { emptyList() }
    )
}

data class ChannelsUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val countries: List<CountryBO> = emptyList(),
    val categories: List<CategoryBO> = emptyList(),
    val channels: List<SimpleChannelBO> = emptyList(),
    val countrySelected: CountryBO? = null,
    val categorySelected: CategoryBO? = null,
    val channelFocused: SimpleChannelBO? = null
): UiState<ChannelsUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): ChannelsUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface ChannelsSideEffects: SideEffect