package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class DeleteProfileUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<DeleteProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        userRepository.deleteProfile(params.profileId)

    data class Params(
        val profileId: String
    )
}