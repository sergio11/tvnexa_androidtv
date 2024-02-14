package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.account

import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetUserDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignOffUseCase
import com.dreamsoftware.tvnexa.ui.core.SideEffect
import com.dreamsoftware.tvnexa.ui.core.SupportViewModel
import com.dreamsoftware.tvnexa.ui.core.UiState
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val signOffUseCase: SignOffUseCase
):
    SupportViewModel<AccountUiState, AccountSideEffects>() {

    override fun onGetDefaultState(): AccountUiState = AccountUiState()

    fun load() {
        executeUseCase(
            useCase = getUserDetailUseCase,
            onSuccess = ::onLoadUserDetailSuccessfully
        )
    }

    fun signOff() {
        updateState {
            it.copy(confirmSignOffDialogVisible = true)
        }
    }

    fun onConfirmSignOff() {
        executeUseCase(useCase = signOffUseCase)
    }

    fun onCancelSignOff() {
        updateState {
            it.copy(confirmSignOffDialogVisible = false)
        }
    }

    private fun onLoadUserDetailSuccessfully(userDetailBO: UserDetailBO) {
        with(userDetailBO) {
            updateState {
                it.copy(
                    uuid = uuid,
                    username = username,
                    email = email,
                    firstName = firstName,
                    lastName = lastName
                )
            }
        }
    }
}

data class AccountUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val uuid: String = String.EMPTY,
    val username: String = String.EMPTY,
    val email: String = String.EMPTY,
    val firstName: String = String.EMPTY,
    val lastName: String = String.EMPTY,
    val confirmSignOffDialogVisible: Boolean = false
): UiState<AccountUiState>(isLoading, error) {
    override fun copyState(isLoading: Boolean, error: String?): AccountUiState =
        copy(isLoading = isLoading, error = error)
}

sealed interface AccountSideEffects: SideEffect

