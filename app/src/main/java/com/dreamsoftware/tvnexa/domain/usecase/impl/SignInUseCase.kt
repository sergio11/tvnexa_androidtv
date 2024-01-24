package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isEmailValid
import com.dreamsoftware.tvnexa.domain.extensions.isPasswordValid
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase(
    private val repository: IAuthRepository
): BaseUseCaseWithParams<SignInUseCase.Params, AuthSessionBO>() {

    override suspend fun onExecuted(params: Params): AuthSessionBO = with(params) {
        if(email.isEmailValid() && password.isPasswordValid()) {
            repository.signIn(email, password)
        } else {
            throw DomainException.InvalidSigInDataException("Invalid SingIn data provided")
        }
    }

    data class Params(
        val email: String,
        val password: String
    )
}