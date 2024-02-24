package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class CreateProfileMapperImpl @Inject constructor(): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> {

    override fun mapInToOut(input: CreateProfileRequestBO): CreateProfileRequestDTO = with(input) {
        CreateProfileRequestDTO(
            alias = alias,
            pin = pin,
            enableNSFW = enableNSFW,
            avatarType = avatarType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestBO>): Iterable<CreateProfileRequestDTO> =
        input.map(::mapInToOut)
}