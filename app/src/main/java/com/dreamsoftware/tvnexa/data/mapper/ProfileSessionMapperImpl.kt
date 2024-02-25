package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import com.dreamsoftware.tvnexa.utils.IMapper
import javax.inject.Inject

class ProfileSessionMapperImpl @Inject constructor(): IMapper<ProfileBO, ProfileSelectedPreferenceDTO> {

    override fun mapInToOut(input: ProfileBO): ProfileSelectedPreferenceDTO = with(input) {
        ProfileSelectedPreferenceDTO(
            uuid = uuid,
            alias = alias,
            enableNSFW = enableNSFW,
            isSecured = isSecured,
            isAdmin = isAdmin,
            type = avatarType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileBO>): Iterable<ProfileSelectedPreferenceDTO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: ProfileSelectedPreferenceDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            enableNSFW = enableNSFW,
            isSecured = isSecured,
            isAdmin = isAdmin,
            avatarType = runCatching { AvatarTypeEnum.valueOf(type) }.getOrDefault(AvatarTypeEnum.BOY)
        )
    }

    override fun mapOutListToInList(input: Iterable<ProfileSelectedPreferenceDTO>): Iterable<ProfileBO> =
        input.map(::mapOutToIn)
}