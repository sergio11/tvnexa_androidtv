package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetChannelDetailUseCase(
    private val userRepository: IUserRepository,
    private val channelRepository: IChannelRepository
) : BaseUseCaseWithParams<GetChannelDetailUseCase.Params, ChannelDetailBO>() {

    override suspend fun onExecuted(params: Params): ChannelDetailBO = with(userRepository.getProfileSelected()) {
        channelRepository.findDetailById(
            profileId = uuid,
            enableNsfw = enableNSFW,
            channelId = params.channelId
        )
    }

    data class Params(
        val channelId: String
    )
}