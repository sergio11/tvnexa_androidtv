package com.dreamsoftware.tvnexa.ui.features.splash

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.usecase.impl.HasMultiplesProfilesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyUserSessionUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val verifyUserSessionUseCase: VerifyUserSessionUseCase,
    private val hasMultiplesProfilesUseCase: HasMultiplesProfilesUseCase
): SupportViewModel<SplashUiState, SplashSideEffects>() {

    override fun onGetDefaultState(): SplashUiState = SplashUiState()

    fun verifySession() {
        viewModelScope.launch {
            onLoading()
            delay(4000)
            verifyUserSessionUseCase.invoke(
                scope = viewModelScope,
                onSuccess = ::onVerifyUserSessionCompleted,
                onError = ::onErrorOccurred
            )
        }
    }

    private fun onVerifyUserSessionCompleted(hasActiveSession: Boolean) {
        onIdle()
        if(hasActiveSession) {
            checkProfiles()
        } else {
            launchSideEffect(SplashSideEffects.UserNotAuthenticated)
        }
    }

    private fun checkProfiles() {
        hasMultiplesProfilesUseCase.invoke(
            scope = viewModelScope,
            onSuccess = ::onCheckProfilesCompleted,
            onError = ::onErrorOccurred
        )
    }

    private fun onCheckProfilesCompleted(hasMultipleProfiles: Boolean) {
        launchSideEffect(
            if(hasMultipleProfiles) {
                SplashSideEffects.ProfileSelectionRequired
            } else {
                SplashSideEffects.UserAlreadyAuthenticated
            }
        )
    }

    private fun onErrorOccurred(ex: Exception) {
        onIdle()
        ex.printStackTrace()
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
    }

    private fun onLoading() {
        updateState { it.copyState(isLoading = true) }
    }

    private fun onIdle() {
        updateState { it.copyState(isLoading = false) }
    }
}

data class SplashUiState(
    override var isLoading: Boolean = false,
    override var error: String? = null,
    val isAuth: Boolean = false
): UiState<SplashUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SplashUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SplashSideEffects: SideEffect {
    data object UserAlreadyAuthenticated: SplashSideEffects
    data object ProfileSelectionRequired: SplashSideEffects
    data object UserNotAuthenticated: SplashSideEffects
}