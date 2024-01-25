package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.exception.FormFieldKey
import com.dreamsoftware.tvnexa.domain.extensions.isEmailNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isPasswordNotValid
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase(
    private val repository: IAuthRepository
): BaseUseCaseWithParams<SignInUseCase.Params, AuthSessionBO>() {

    override suspend fun onExecuted(params: Params): AuthSessionBO =
        validateData(params)?.let {
            throw DomainException.InvalidSigInDataException("Invalid data",
                field = it.first, value = it.second)
        } ?: with(params) {
            repository.signIn(email, password)
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