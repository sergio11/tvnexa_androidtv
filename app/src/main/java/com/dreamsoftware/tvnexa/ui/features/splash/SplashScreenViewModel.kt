package com.dreamsoftware.tvnexa.ui.features.splash

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyUserSessionUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val verifyUserSessionUseCase: VerifyUserSessionUseCase
): SupportViewModel<SplashUiState, SplashSideEffects>() {

    override fun onGetDefaultState(): SplashUiState = SplashUiState()

    fun verifySession() {
        onLoading()
        verifyUserSessionUseCase.invoke(
            scope = viewModelScope,
            onSuccess = ::onSuccess,
            onError = ::onErrorOccurred
        )
    }

    private fun onSuccess(hasActiveSession: Boolean) {
        onIdle()
        launchSideEffect(
            if(hasActiveSession) {
                SplashSideEffects.UserAlreadyAuthenticated
            } else {
                SplashSideEffects.UserNotAuthenticated
            }
        )
    }

    private fun onErrorOccurred(ex: Exception) {
        onIdle()
        ex.printStackTrace()
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
    }

    private fun onLoading() {
        updateState { it.copy(isLoading = true) }
    }

    private fun onIdle() {
        updateState { it.copy(isLoading = false) }
    }
}

data class SplashUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val isAuth: Boolean = false
): UiState

sealed interface SplashSideEffects: SideEffect {
    data object UserAlreadyAuthenticated: SplashSideEffects
    data object UserNotAuthenticated: SplashSideEffects
}