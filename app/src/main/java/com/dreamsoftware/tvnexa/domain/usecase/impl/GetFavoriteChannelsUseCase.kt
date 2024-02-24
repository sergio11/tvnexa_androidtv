package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetFavoriteChannelsUseCase(
    private val channelRepository: IChannelRepository,
    private val userRepository: IUserRepository
): BaseUseCase<List<SimpleChannelBO>>() {

    override suspend fun onExecuted(): List<SimpleChannelBO> {
        val currentProfileSelected = userRepository.getProfileSelected()
        return channelRepository.getFavoriteChannels(currentProfileSelected.uuid)
    }
}