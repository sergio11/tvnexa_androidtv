package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SignInUseCase(
    private val repository: IAuthRepository
): BaseUseCaseWithParams<SignInUseCase.Params, AuthSessionBO>() {

    override suspend fun onExecuted(params: Params): AuthSessionBO = with(params) {
        repository.signIn(email, password)
    }

    data class Params(
        val email: String,
        val password: String
    )
}