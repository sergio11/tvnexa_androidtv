package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class AuthResponseMapperImpl @Inject constructor(): IOneSideMapper<AuthResponseDTO, AuthSessionBO> {

    override fun mapInToOut(input: AuthResponseDTO): AuthSessionBO = with(input) {
        AuthSessionBO(
            uuid = user.uuid,
            username = user.username,
            token = token
        )
    }

    override fun mapInListToOutList(input: Iterable<AuthResponseDTO>): Iterable<AuthSessionBO> =
        input.map(::mapInToOut)
}