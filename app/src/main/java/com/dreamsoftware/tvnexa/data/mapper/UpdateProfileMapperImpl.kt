package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class UpdateProfileMapperImpl @Inject constructor(): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO> {

    override fun mapInToOut(input: UpdatedProfileRequestBO): UpdatedProfileRequestDTO = with(input) {
        UpdatedProfileRequestDTO(
            alias = alias,
            pin = pin,
            enableNSFW = enableNSFW,
            avatarType = avatarType
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedProfileRequestBO>): Iterable<UpdatedProfileRequestDTO> =
        input.map(::mapInToOut)
}