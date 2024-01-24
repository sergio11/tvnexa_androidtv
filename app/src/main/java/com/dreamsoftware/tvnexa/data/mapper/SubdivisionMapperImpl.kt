package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class SubdivisionMapperImpl @Inject constructor(
    private val countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>
): IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO> {

    override fun mapInToOut(input: SubdivisionResponseDTO): SubdivisionBO = with(input) {
        SubdivisionBO(
            code = code,
            name = name,
            country = countryMapper.mapInToOut(country)
        )
    }

    override fun mapInListToOutList(input: Iterable<SubdivisionResponseDTO>): Iterable<SubdivisionBO> =
        input.map(::mapInToOut)
}