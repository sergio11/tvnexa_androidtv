package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.LanguageResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.LanguageBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class CountryMapperImpl @Inject constructor(
    private val languagesMapper: IOneSideMapper<LanguageResponseDTO, LanguageBO>
): IOneSideMapper<CountryResponseDTO, CountryBO> {

    override fun mapInToOut(input: CountryResponseDTO): CountryBO = with(input) {
        CountryBO(
            code = code,
            name = name,
            flag = flag,
            languages = languagesMapper.mapInListToOutList(languages).toList()
        )
    }

    override fun mapInListToOutList(input: Iterable<CountryResponseDTO>): Iterable<CountryBO> =
        input.map(::mapInToOut)
}