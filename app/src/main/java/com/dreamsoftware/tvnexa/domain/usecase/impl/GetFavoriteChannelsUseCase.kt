package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class GetFavoriteChannelsUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<GetFavoriteChannelsUseCase.Params, List<SimpleChannelBO>>() {

    override suspend fun onExecuted(params: Params): List<SimpleChannelBO> =
        userRepository.getFavoriteChannels(params.profileId)

    data class Params(
        val profileId: String
    )
}