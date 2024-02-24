package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class SaveFavoriteChannelUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<SaveFavoriteChannelUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params) {
        with(userRepository) {
            saveFavoriteChannels(
                profileId = getProfileSelected().uuid,
                channelId = params.channelId
            )
        }
    }

    data class Params(
        val channelId: String
    )
}