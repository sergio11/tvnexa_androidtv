package com.dreamsoftware.tvnexa.domain.usecase

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.ProfileTypeEnum
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetProfileByIdUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<GetProfileByIdUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        ProfileBO(
            uuid = params.profileId,
            alias = "Sergio 2",
            isAdmin = true,
            type = ProfileTypeEnum.BOY
        )

    data class Params(
        val profileId: String
    )
}