package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetProfileByIdUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<GetProfileByIdUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        userRepository.getProfileById(params.profileId)

    data class Params(
        val profileId: String
    )
}