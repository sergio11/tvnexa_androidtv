package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class SimpleChannelMapperImpl @Inject constructor(): IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO> {

    override fun mapInToOut(input: SimpleChannelResponseDTO): SimpleChannelBO = with(input) {
        SimpleChannelBO(
            channelId = channelId,
            name = name,
            city = city,
            isNsfw = isNsfw,
            website = website,
            logo = logo
        )
    }

    override fun mapInListToOutList(input: Iterable<SimpleChannelResponseDTO>): Iterable<SimpleChannelBO> =
        input.map(::mapInToOut)
}