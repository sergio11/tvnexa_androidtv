package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetProfileSelectedUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<ProfileBO>() {
    override suspend fun onExecuted(): ProfileBO =
        userRepository.getProfileSelected()
}