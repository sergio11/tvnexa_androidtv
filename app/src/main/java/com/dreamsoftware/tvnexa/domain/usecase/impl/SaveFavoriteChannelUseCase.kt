package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SaveFavoriteChannelUseCase(
    private val userRepository: IUserRepository,
    private val channelRepository: IChannelRepository
): BaseUseCaseWithParams<SaveFavoriteChannelUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params) {
        val currentProfileSelected = userRepository.getProfileSelected()
        channelRepository.saveFavoriteChannels(
            profileId = currentProfileSelected.uuid,
            channelId = params.channelId
        )
    }

    data class Params(
        val channelId: String
    )
}