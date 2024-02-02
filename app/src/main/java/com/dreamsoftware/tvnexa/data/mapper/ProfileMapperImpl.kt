package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.ProfileTypeEnum
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class ProfileMapperImpl @Inject constructor(): IOneSideMapper<ProfileResponseDTO, ProfileBO> {

    override fun mapInToOut(input: ProfileResponseDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isAdmin = isAdmin,
            type = runCatching { ProfileTypeEnum.valueOf(type) }.getOrDefault(ProfileTypeEnum.BOY)
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileResponseDTO>): Iterable<ProfileBO> =
        input.map(::mapInToOut)
}