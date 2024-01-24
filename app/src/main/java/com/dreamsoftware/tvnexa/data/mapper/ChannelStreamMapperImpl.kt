package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelStreamResponseDTO
import com.dreamsoftware.tvnexa.domain.model.ChannelStreamBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class ChannelStreamMapperImpl @Inject constructor(): IOneSideMapper<ChannelStreamResponseDTO, ChannelStreamBO> {

    override fun mapInToOut(input: ChannelStreamResponseDTO): ChannelStreamBO = with(input) {
        ChannelStreamBO(
            code = code,
            url = url,
            httpReferrer = httpReferrer,
            userAgent = userAgent
        )
    }

    override fun mapInListToOutList(input: Iterable<ChannelStreamResponseDTO>): Iterable<ChannelStreamBO> =
        input.map(::mapInToOut)
}