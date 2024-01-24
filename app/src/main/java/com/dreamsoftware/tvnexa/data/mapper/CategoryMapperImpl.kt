package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(): IOneSideMapper<CategoryResponseDTO, CategoryBO> {

    override fun mapInToOut(input: CategoryResponseDTO): CategoryBO = with(input) {
        CategoryBO(id = id, name = name)
    }

    override fun mapInListToOutList(input: Iterable<CategoryResponseDTO>): Iterable<CategoryBO> =
        input.map(::mapInToOut)
}