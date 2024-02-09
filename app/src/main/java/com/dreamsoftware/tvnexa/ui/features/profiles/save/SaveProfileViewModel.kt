package com.dreamsoftware.tvnexa.ui.features.profiles.save

import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveProfileViewModel @Inject constructor(): SupportViewModel<SaveProfileUiState, SaveProfileSideEffects>() {

    override fun onGetDefaultState(): SaveProfileUiState = SaveProfileUiState()

}

data class SaveProfileUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
): UiState<SaveProfileUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SaveProfileUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SaveProfileSideEffects: SideEffect