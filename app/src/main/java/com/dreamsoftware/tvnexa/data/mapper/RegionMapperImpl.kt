package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class RegionMapperImpl @Inject constructor(): IOneSideMapper<RegionResponseDTO, RegionBO> {

    override fun mapInToOut(input: RegionResponseDTO): RegionBO = with(input) {
        RegionBO(code = code, name = name)
    }

    override fun mapInListToOutList(input: Iterable<RegionResponseDTO>): Iterable<RegionBO> =
        input.map(::mapInToOut)
}