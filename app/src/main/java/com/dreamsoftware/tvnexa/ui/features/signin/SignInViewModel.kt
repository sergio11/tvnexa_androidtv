package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignInUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.ui.core.IFormErrorMapper
import com.dreamsoftware.tvnexa.ui.features.signin.error.SignInScreenSimpleErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInScreenErrorMapper: SignInScreenSimpleErrorMapper,
    private val formErrorMapper: IFormErrorMapper
): SupportViewModel<SignInUiState, SignInSideEffects>() {
    override fun onGetDefaultState(): SignInUiState = SignInUiState()

    fun onSignIn() {
        onLoading()
        with(uiState.value) {
            signInUseCase.invoke(
                scope = viewModelScope,
                params = SignInUseCase.Params(email, password),
                onSuccess = {
                    onSignInSuccessfully()
                },
                onError = ::onErrorOccurred
            )
        }
    }

    fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword,) }
    }

    fun onErrorAccepted() {
        updateState { it.copy(error = null) }
    }

    private fun onLoading() {
        updateState { it.copy(isLoading = true) }
    }

    private fun onIdle() {
        updateState { it.copy(isLoading = false) }
    }

    private fun onSignInSuccessfully() {
        onIdle()
        launchSideEffect(SignInSideEffects.AuthenticationSuccessfully)
    }

    private fun onErrorOccurred(ex: Exception) {
        ex.printStackTrace()
        updateState {
            it.copy(
                isLoading = false,
                error = signInScreenErrorMapper.mapToMessage(ex),
                emailError = formErrorMapper.mapToMessage(key = FormFieldKey.EMAIL, ex),
                passwordError = formErrorMapper.mapToMessage(key = FormFieldKey.PASSWORD,  ex)
            )
        }
    }
}

data class SignInUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val email: String = String.EMPTY,
    val emailError: String = String.EMPTY,
    val password: String = String.EMPTY,
    val passwordError: String = String.EMPTY
): UiState

sealed interface SignInSideEffects: SideEffect {
    data object AuthenticationSuccessfully: SignInSideEffects
}