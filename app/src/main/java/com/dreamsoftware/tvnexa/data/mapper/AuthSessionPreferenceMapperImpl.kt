package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.utils.IMapper
import javax.inject.Inject

class AuthSessionPreferenceMapperImpl @Inject constructor(): IMapper<AuthSessionPreferenceDTO, AuthSessionBO> {

    override fun mapInToOut(input: AuthSessionPreferenceDTO): AuthSessionBO = with(input) {
        AuthSessionBO(
            uuid = uuid,
            username = username,
            token = token
        )
    }

    override fun mapInListToOutList(input: Iterable<AuthSessionPreferenceDTO>): Iterable<AuthSessionBO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: AuthSessionBO): AuthSessionPreferenceDTO = with(input) {
        AuthSessionPreferenceDTO(
            uuid = uuid,
            username = username,
            token = token
        )
    }

    override fun mapOutListToInList(input: Iterable<AuthSessionBO>): Iterable<AuthSessionPreferenceDTO> =
        input.map(::mapOutToIn)
}