package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignUpUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): SupportViewModel<SignUpUiState, SignUpSideEffects>() {
    override fun onGetDefaultState(): SignUpUiState = SignUpUiState()

    fun onSignUp() {
        onLoading()
        with(uiState.value) {
            signUpUseCase.invoke(
                scope = viewModelScope,
                params = SignUpUseCase.Params(
                    username = username,
                    repeatPassword = repeatPassword,
                    password = password,
                    email = email,
                    firstName = firstName,
                    lastName = lastName
                ),
                onSuccess = {
                    onSigUpSuccessfully()
                },
                onError = ::onErrorOccurred
            )
        }
    }

    fun onUsernameChanged(newUsername: String) {
        updateState { it.copy(username = newUsername) }
    }

    fun onFirstNameChanged(newFirstName: String) {
        updateState { it.copy(firstName = newFirstName) }
    }

    fun onLastNameChanged(newLastName: String) {
        updateState { it.copy(lastName = newLastName) }
    }

    fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword,) }
    }

    fun onRepeatPasswordChanged(newRepeatPassword: String) {
        updateState { it.copy(repeatPassword = newRepeatPassword,) }
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

    private fun onSigUpSuccessfully() {
        onIdle()
        launchSideEffect(SignUpSideEffects.RegisteredSuccessfully)
    }

    private fun onErrorOccurred(ex: Exception) {
        ex.printStackTrace()
        updateState {
            it.copy(
                isLoading = false
            )
        }
    }
}

data class SignUpUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val username: String = String.EMPTY,
    val usernameError: String = String.EMPTY,
    val email: String = String.EMPTY,
    val emailError: String = String.EMPTY,
    val password: String = String.EMPTY,
    val passwordError: String = String.EMPTY,
    val repeatPassword: String = String.EMPTY,
    val repeatPasswordError: String = String.EMPTY,
    val firstName: String = String.EMPTY,
    val firstNameError: String = String.EMPTY,
    val lastName: String = String.EMPTY,
    val lastNameError: String = String.EMPTY,
): UiState

sealed interface SignUpSideEffects: SideEffect {
    data object RegisteredSuccessfully: SignUpSideEffects
}