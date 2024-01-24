package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetCategoriesUseCase(
    private val categoryRepository: ICategoryRepository
) : BaseUseCase<List<CategoryBO>>() {

    override suspend fun onExecuted(): List<CategoryBO> =
        categoryRepository.findAll()
}