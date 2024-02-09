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
            delay(4000)
            executeUseCase(
                useCase = verifyUserSessionUseCase,
                onSuccess = ::onVerifyUserSessionCompleted,
                onFailed = ::onVerifyUserSessionFailed
            )
        }
    }

    private fun checkProfiles() {
        executeUseCase(
            useCase = hasMultiplesProfilesUseCase,
            onSuccess = ::onCheckProfilesCompleted,
            onFailed = ::onCheckProfilesFailed
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

    private fun onCheckProfilesFailed() {
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
    }

    private fun onVerifyUserSessionCompleted(hasActiveSession: Boolean) {
        if(hasActiveSession) {
            checkProfiles()
        } else {
            launchSideEffect(SplashSideEffects.UserNotAuthenticated)
        }
    }

    private fun onVerifyUserSessionFailed() {
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
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