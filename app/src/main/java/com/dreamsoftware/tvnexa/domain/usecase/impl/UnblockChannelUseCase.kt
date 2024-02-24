package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class UnblockChannelUseCase(
    private val userRepository: IUserRepository,
    private val channelRepository: IChannelRepository
): BaseUseCaseWithParams<UnblockChannelUseCase.Params, Unit>() {
    override suspend fun onExecuted(params: Params) {
        val currentProfileSelected = userRepository.getProfileSelected()
        channelRepository.unblockChannel(
            profileId = currentProfileSelected.uuid,
            channelId = params.channelId
        )
    }

    data class Params(
        val channelId: String
    )
}