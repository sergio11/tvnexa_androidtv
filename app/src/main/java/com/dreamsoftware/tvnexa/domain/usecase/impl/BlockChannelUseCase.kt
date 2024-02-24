package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class BlockChannelUseCase(
    private val channelRepository: IChannelRepository,
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<BlockChannelUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params) {
        val currentProfileSelected = userRepository.getProfileSelected()
        channelRepository.blockChannel(
            profileId = currentProfileSelected.uuid,
            channelId = params.channelId
        )
    }

    data class Params(
        val channelId: String
    )
}