package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class VerifyChannelSavedAsFavoriteUseCase(
    private val channelRepository: IChannelRepository,
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<VerifyChannelSavedAsFavoriteUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean {
        val currentProfileSelected = userRepository.getProfileSelected()
        return channelRepository.isChannelSavedAsFavorite(
            profileId = currentProfileSelected.uuid,
            channelId = params.channelId
        )
    }

    data class Params(
        val channelId: String
    )
}