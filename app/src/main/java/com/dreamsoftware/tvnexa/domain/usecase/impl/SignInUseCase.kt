package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isEmailNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isPasswordNotValid
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.tvnexa.utils.ISessionAware

class SignInUseCase(
    private val repository: IAuthRepository,
    private val sessionAware: ISessionAware
): BaseUseCaseWithParams<SignInUseCase.Params, AuthenticationBO>() {

    override suspend fun onExecuted(params: Params): AuthenticationBO =
        validateData(params)?.let {
            throw DomainException.InvalidDataException("Invalid data",
                field = it.first, value = it.second)
        } ?: with(params) {
            with(repository) {
                signIn(email, password).also {
                    sessionAware.session = getActiveSession()
                }
            }
        }

    private fun validateData(params: Params): Pair<FormFieldKey, String>? = with(params) {
        when {
            email.isEmailNotValid() -> FormFieldKey.EMAIL to email
            password.isPasswordNotValid() -> FormFieldKey.PASSWORD to password
            else -> null
        }
    }

    data class Params(
        val email: String,
        val password: String
    )
}