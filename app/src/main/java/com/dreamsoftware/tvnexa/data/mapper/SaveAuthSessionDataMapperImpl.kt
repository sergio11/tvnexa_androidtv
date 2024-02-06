package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class SaveAuthSessionDataMapperImpl @Inject constructor(): IOneSideMapper<AuthenticationBO, AuthSessionPreferenceDTO> {

    override fun mapInToOut(input: AuthenticationBO): AuthSessionPreferenceDTO = with(input) {
        AuthSessionPreferenceDTO(
            uuid = uuid,
            token = token,
        )
    }

    override fun mapInListToOutList(input: Iterable<AuthenticationBO>): Iterable<AuthSessionPreferenceDTO> =
        input.map(::mapInToOut)
}