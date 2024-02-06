package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class ReadAuthSessionDataMapperImpl @Inject constructor(): IOneSideMapper<AuthSessionPreferenceDTO, AuthSessionBO> {

    override fun mapInToOut(input: AuthSessionPreferenceDTO): AuthSessionBO = with(input) {
        AuthSessionBO(
            uuid = uuid,
            token = token,
        )
    }

    override fun mapInListToOutList(input: Iterable<AuthSessionPreferenceDTO>): Iterable<AuthSessionBO> =
        input.map(::mapInToOut)
}