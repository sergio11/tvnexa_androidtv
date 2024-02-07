package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class HasMultiplesProfilesUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<Boolean>() {
    override suspend fun onExecuted(): Boolean =
        userRepository.getProfiles().size > 1
}