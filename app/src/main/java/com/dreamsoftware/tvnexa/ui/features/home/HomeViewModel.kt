package com.dreamsoftware.tvnexa.ui.features.home

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileSelectedUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data.MenuData
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProfileSelectedUseCase: GetProfileSelectedUseCase
) : SupportViewModel<HomeUiState, HomeSideEffects>() {

    override fun onGetDefaultState(): HomeUiState = HomeUiState(
        mainMenuItems = MenuData.mainMenuItems,
        secondaryMenuItems = MenuData.secondaryMenuItems
    )

    fun load() {
        executeUseCase(
            useCase = getProfileSelectedUseCase,
            onSuccess = ::onProfileLoadSuccessfully
        )
    }

    fun onMenuItemSelected(id: String) {
        updateState { it.copy(menuItemIdSelected = id) }
    }

    private fun onProfileLoadSuccessfully(profile: ProfileBO) {
        updateState { it.copy(profileSelected = profile) }
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val mainMenuItems: List<MenuItem> = emptyList(),
    val secondaryMenuItems: List<MenuItem> = emptyList(),
    val menuIsOpened: Boolean = false,
    val menuItemIdSelected: String = String.EMPTY,
    val profileSelected: ProfileBO? = null
): UiState<HomeUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): HomeUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface HomeSideEffects: SideEffect
