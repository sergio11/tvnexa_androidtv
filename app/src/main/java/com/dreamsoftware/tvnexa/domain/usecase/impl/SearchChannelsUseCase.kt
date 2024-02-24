package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SearchChannelsUseCase(
    private val userRepository: IUserRepository,
    private val channelRepository: IChannelRepository
) : BaseUseCaseWithParams<SearchChannelsUseCase.Params, List<SimpleChannelBO>>() {

    override suspend fun onExecuted(params: Params): List<SimpleChannelBO> = with(userRepository.getProfileSelected()) {
        channelRepository.findByTerm(
            profileId = uuid,
            enableNsfw = enableNSFW,
            term = params.term
        )
    }

    data class Params(
        val term: String
    )
}