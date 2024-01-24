package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelGuideResponseDTO
import com.dreamsoftware.tvnexa.domain.model.ChannelGuideBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class ChannelGuideMapperImpl @Inject constructor(): IOneSideMapper<ChannelGuideResponseDTO, ChannelGuideBO> {

    override fun mapInToOut(input: ChannelGuideResponseDTO): ChannelGuideBO = with(input) {
        ChannelGuideBO(
            code = code,
            site = site,
            siteId = siteId,
            siteName = siteName,
            lang = lang
        )
    }

    override fun mapInListToOutList(input: Iterable<ChannelGuideResponseDTO>): Iterable<ChannelGuideBO> =
        input.map(::mapInToOut)
}