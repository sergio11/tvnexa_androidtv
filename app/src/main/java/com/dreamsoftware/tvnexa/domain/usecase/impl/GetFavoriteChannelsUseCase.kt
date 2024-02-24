package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetFavoriteChannelsUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<List<SimpleChannelBO>>() {

    override suspend fun onExecuted(): List<SimpleChannelBO> = with(userRepository) {
        getFavoriteChannels(profileId = getProfileSelected().uuid)
    }
}