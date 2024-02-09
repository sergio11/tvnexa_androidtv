package com.dreamsoftware.tvnexa.ui.features.signin

import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignInUseCase
import com.dreamsoftware.tvnexa.ui.core.IFormErrorMapper
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
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
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = signInUseCase,
                params = SignInUseCase.Params(email, password),
                onSuccess = ::onSignInSuccessfully,
                onMapExceptionToState = ::onMapExceptionToState
            )
        }
    }

    fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword,) }
    }

    private fun onSignInSuccessfully(authenticationBO: AuthenticationBO) {
        launchSideEffect(if(authenticationBO.profilesCount > 0) {
            SignInSideEffects.ProfileSelectionRequired
        } else {
            SignInSideEffects.AuthenticationSuccessfully
        })
    }

    private fun onMapExceptionToState(ex: Exception, uiState: SignInUiState) =
        uiState.copy(
            error = signInScreenErrorMapper.mapToMessage(ex),
            emailError = formErrorMapper.mapToMessage(key = FormFieldKey.EMAIL, ex),
            passwordError = formErrorMapper.mapToMessage(key = FormFieldKey.PASSWORD,  ex)
        )
}

data class SignInUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val email: String = "ssanchez5@tvnexa.com",
    val emailError: String = String.EMPTY,
    val password: String = "12345678",
    val passwordError: String = String.EMPTY
): UiState<SignInUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): SignInUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface SignInSideEffects: SideEffect {
    data object AuthenticationSuccessfully: SignInSideEffects
    data object ProfileSelectionRequired: SignInSideEffects
}