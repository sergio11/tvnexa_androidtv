package com.dreamsoftware.tvnexa.domain.usecase.impl

import android.util.Log
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class VerifyUserSessionUseCase(
    private val authRepository: IAuthRepository
): BaseUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching {
        Log.d("ATV_VERIFY_SESSION", "VerifyUserSessionUseCase onExecuted")
        authRepository.hasActiveSession()
    }.getOrDefault(false)
}