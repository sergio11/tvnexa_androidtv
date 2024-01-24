package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isFirstNameNotValid
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.tvnexa.domain.exception.DomainException.InvalidSigUpDataException.FieldErrorName
import com.dreamsoftware.tvnexa.domain.extensions.isLastNameNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isPasswordNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isUsernameNotValid
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO

class SignUpUseCase(
    private val authRepository: IAuthRepository
): BaseUseCaseWithParams<SignUpUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        validateData(params)?.let {
            throw DomainException.InvalidSigUpDataException("Invalid Data provided", field = it)
        } ?: with(params) {
            authRepository.signUp(SaveUserBO(
                username = username,
                password = password,
                email = email,
                firstName = firstName,
                lastName = lastName
            ))
        }

    private fun validateData(params: Params): FieldErrorName? = with(params) {
        when {
            username.isUsernameNotValid() -> FieldErrorName.USERNAME
            password.isPasswordNotValid() -> FieldErrorName.PASSWORD
            firstName.isFirstNameNotValid() -> FieldErrorName.FIRST_NAME
            lastName.isLastNameNotValid() -> FieldErrorName.LAST_NAME
            else -> null
        }
    }

    data class Params(
        val username: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String
    )
}