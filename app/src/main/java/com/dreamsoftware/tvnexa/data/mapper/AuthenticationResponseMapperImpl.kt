package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class AuthenticationResponseMapperImpl @Inject constructor(): IOneSideMapper<AuthResponseDTO, AuthenticationBO> {

    override fun mapInToOut(input: AuthResponseDTO): AuthenticationBO = with(input) {
        AuthenticationBO(
            uuid = user.uuid,
            username = user.username,
            token = token,
            profilesCount = profilesCount
        )
    }

    override fun mapInListToOutList(input: Iterable<AuthResponseDTO>): Iterable<AuthenticationBO> =
        input.map(::mapInToOut)
}