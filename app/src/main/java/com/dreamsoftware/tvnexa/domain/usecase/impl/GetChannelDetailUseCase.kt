package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetChannelDetailUseCase(
    private val channelRepository: IChannelRepository
) : BaseUseCaseWithParams<GetChannelDetailUseCase.Params, ChannelDetailBO>() {

    override suspend fun onExecuted(params: Params): ChannelDetailBO =
        channelRepository.findDetailById(params.channelId)

    data class Params(
        val channelId: String
    )
}