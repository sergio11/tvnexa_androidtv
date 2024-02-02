package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class UpdateUserMapperImpl @Inject constructor(): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> {

    override fun mapInToOut(input: UpdatedUserRequestBO): UpdatedUserRequestDTO = with(input) {
        UpdatedUserRequestDTO(
            firstName = firstName,
            lastName = lastName,
            username = username
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedUserRequestBO>): Iterable<UpdatedUserRequestDTO> =
        input.map(::mapInToOut)
}