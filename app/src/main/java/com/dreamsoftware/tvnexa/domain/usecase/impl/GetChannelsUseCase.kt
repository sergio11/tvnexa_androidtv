package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetChannelsUseCase(
    private val channelsRepository: IChannelRepository
) : BaseUseCaseWithParams<GetChannelsUseCase.Params, List<SimpleChannelBO>>() {

    override suspend fun onExecuted(params: Params): List<SimpleChannelBO> = with(params) {
        channelsRepository.findBy(category, country, offset, limit)
    }

    data class Params(
        val category: String?,
        val country: String?,
        val offset: Long,
        val limit: Long
    )
}