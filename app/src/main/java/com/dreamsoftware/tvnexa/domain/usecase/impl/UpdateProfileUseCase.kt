package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class UpdateProfileUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<UpdateProfileUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO = with(params) {
        userRepository.updateProfile(profileId, UpdatedProfileRequestBO(alias, pin, isAdmin, type))
    }

    data class Params(
        val profileId: String,
        val alias: String?,
        val pin: Int?,
        val isAdmin: Boolean?,
        val type: String?
    )
}