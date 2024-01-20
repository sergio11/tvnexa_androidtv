package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetSessionUseCase(
    private val authRepository: IAuthRepository
) : BaseUseCase<AuthSessionBO>() {

    override suspend fun onExecuted(): AuthSessionBO =
        authRepository.getSession()
}