package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class VerifyPinUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<VerifyPinUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        userRepository.verifyPin(profileId, pin)
    }

    data class Params(
        val profileId: String,
        val pin: Int
    )
}