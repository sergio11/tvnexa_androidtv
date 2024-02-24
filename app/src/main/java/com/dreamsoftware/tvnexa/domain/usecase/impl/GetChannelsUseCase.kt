package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetChannelsUseCase(
    private val userRepository: IUserRepository,
    private val channelsRepository: IChannelRepository
) : BaseUseCaseWithParams<GetChannelsUseCase.Params, List<SimpleChannelBO>>() {

    override suspend fun onExecuted(params: Params): List<SimpleChannelBO> = with(params) {
        with(userRepository.getProfileSelected()) {
            channelsRepository.findBy(
                profileId = uuid,
                enableNsfw = enableNSFW,
                category = category,
                country = country,
                offset = offset,
                limit = limit
            )
        }
    }

    data class Params(
        val category: String?,
        val country: String?,
        val offset: Long,
        val limit: Long
    )
}