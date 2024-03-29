package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetProfilesUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<List<ProfileBO>>() {
    override suspend fun onExecuted(): List<ProfileBO> =
        userRepository.getProfiles()
}