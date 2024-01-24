package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelGuideResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelStreamResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.LanguageResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.ChannelGuideBO
import com.dreamsoftware.tvnexa.domain.model.ChannelStreamBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.LanguageBO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class ChannelDetailMapperImpl @Inject constructor(
    private val channelStreamMapper: IOneSideMapper<ChannelStreamResponseDTO, ChannelStreamBO>,
    private val channelGuideMapper: IOneSideMapper<ChannelGuideResponseDTO, ChannelGuideBO>,
    private val languageMapper: IOneSideMapper<LanguageResponseDTO, LanguageBO>,
    private val categoryMapper: IOneSideMapper<CategoryResponseDTO, CategoryBO>,
    private val countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>,
    private val subdivisionMapper: IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>
): IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO> {

    override fun mapInToOut(input: ChannelDetailResponseDTO): ChannelDetailBO = with(input) {
        ChannelDetailBO(
            channelId = channelId,
            name = name,
            network = network,
            country = countryMapper.mapInToOut(country),
            subdivision = subdivision?.let(subdivisionMapper::mapInToOut),
            city = city,
            isNsfw = isNsfw,
            website = website,
            logo = logo,
            launched = launched,
            closed = closed,
            replacedBy = replacedBy?.let(::mapInToOut),
            streams = channelStreamMapper.mapInListToOutList(streams).toList(),
            guides = channelGuideMapper.mapInListToOutList(guides).toList(),
            languages = languageMapper.mapInListToOutList(languages).toList(),
            categories = categoryMapper.mapInListToOutList(categories).toList(),
            altNames = altNames,
            owners = owners,
            broadcastAreas = broadcastAreas
        )
    }

    override fun mapInListToOutList(input: Iterable<ChannelDetailResponseDTO>): Iterable<ChannelDetailBO> =
        input.map(::mapInToOut)
}