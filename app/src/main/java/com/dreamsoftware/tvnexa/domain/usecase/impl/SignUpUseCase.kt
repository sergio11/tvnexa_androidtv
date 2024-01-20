package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SignUpUseCase(
    private val authRepository: IAuthRepository
): BaseUseCaseWithParams<SignUpUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        authRepository.signUp(user)
    }

    data class Params(
        val user: SaveUserBO
    )
}