package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isFirstNameNotValid
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.tvnexa.domain.extensions.isLastNameNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isPasswordNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isUsernameNotValid
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO

class SignUpUseCase(
    private val authRepository: IAuthRepository
): BaseUseCaseWithParams<SignUpUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        validateData(params)?.let {
            throw DomainException.InvalidDataException("Invalid Data provided", field = it.first, value = it.second)
        } ?: with(params) {
            authRepository.signUp(SaveUserBO(
                username = username,
                password = password,
                email = email,
                firstName = firstName,
                lastName = lastName
            ))
        }

    private fun validateData(params: Params): Pair<FormFieldKey, String>? = with(params) {
        when {
            username.isUsernameNotValid() -> FormFieldKey.USERNAME to username
            password.isPasswordNotValid() -> FormFieldKey.PASSWORD to password
            firstName.isFirstNameNotValid() -> FormFieldKey.FIRST_NAME to firstName
            lastName.isLastNameNotValid() -> FormFieldKey.LAST_NAME to lastName
            else -> null
        }
    }

    data class Params(
        val username: String,
        val repeatPassword: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String
    )
}