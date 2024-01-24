package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.LanguageResponseDTO
import com.dreamsoftware.tvnexa.domain.model.LanguageBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class LanguageMapperImpl @Inject constructor(): IOneSideMapper<LanguageResponseDTO, LanguageBO> {

    override fun mapInToOut(input: LanguageResponseDTO): LanguageBO = with(input) {
        LanguageBO(code = code, name = name)
    }

    override fun mapInListToOutList(input: Iterable<LanguageResponseDTO>): Iterable<LanguageBO> =
        input.map(::mapInToOut)
}