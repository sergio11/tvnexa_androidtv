package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase
import com.dreamsoftware.tvnexa.utils.ISessionAware

class VerifyUserSessionUseCase(
    private val authRepository: IAuthRepository,
    private val sessionAware: ISessionAware
): BaseUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching {
        authRepository.getActiveSession().also {
            sessionAware.session = it
        }
        true
    }.getOrDefault(false)
}