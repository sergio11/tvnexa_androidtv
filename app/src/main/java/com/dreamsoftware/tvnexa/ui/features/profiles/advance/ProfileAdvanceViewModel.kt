package com.dreamsoftware.tvnexa.ui.features.profiles.advance

import androidx.annotation.StringRes
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileAdvanceViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): SupportViewModel<ProfileAdvanceUiState, ProfileAdvanceSideEffects>() {

    override fun onGetDefaultState(): ProfileAdvanceUiState = ProfileAdvanceUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onNewTabSelected(newTab: ProfileAdvancedTab) {
        updateState {
            it.copy(tabSelected = newTab)
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }
}

data class ProfileAdvanceUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val profile: ProfileBO? = null,
    val tabs: List<ProfileAdvancedTab> = listOf(
        ProfileAdvancedTab.ChangeSecurePinTab,
        ProfileAdvancedTab.TimeRestrictionsTab
    ),
    val tabSelected: ProfileAdvancedTab = ProfileAdvancedTab.ChangeSecurePinTab
): UiState<ProfileAdvanceUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): ProfileAdvanceUiState =
        copy(isLoading = isLoading, error = error)
}


sealed class ProfileAdvancedTab(
    @StringRes val titleRes: Int
) {
    data object ChangeSecurePinTab: ProfileAdvancedTab(
        titleRes = R.string.profiles_advance_change_pin_tab_label_text
    )

    data object TimeRestrictionsTab: ProfileAdvancedTab(
        titleRes = R.string.profiles_advance_time_restrictions_tab_label_text
    )
}


sealed interface ProfileAdvanceSideEffects: SideEffect