package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class VerifyUserSessionUseCase(
    private val authRepository: IAuthRepository
): BaseUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching {
        authRepository.getSession()
        true
    }.getOrDefault(false)
}