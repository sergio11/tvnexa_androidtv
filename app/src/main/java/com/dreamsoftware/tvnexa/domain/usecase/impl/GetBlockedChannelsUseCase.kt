package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetBlockedChannelsUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<GetBlockedChannelsUseCase.Params, List<SimpleChannelBO>>() {

    override suspend fun onExecuted(params: Params): List<SimpleChannelBO> =
        userRepository.getBlockedChannels(params.profileId)

    data class Params(
        val profileId: String
    )
}