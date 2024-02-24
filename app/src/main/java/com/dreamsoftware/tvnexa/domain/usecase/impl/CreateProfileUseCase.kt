package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class CreateProfileUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<CreateProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        userRepository.createProfile(CreateProfileRequestBO(
            alias = alias,
            pin = pin,
            enableNSFW = enableNSFW,
            avatarType = avatarType
        ))
    }
    data class Params(
        val alias: String,
        val pin: Int,
        val enableNSFW: Boolean,
        val avatarType: AvatarTypeEnum
    )
}