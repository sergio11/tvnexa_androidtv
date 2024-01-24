package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CategoryRepositoryImpl(
    private val categoryDataSource: ICategoryDataSource,
    private val categoryMapper: IOneSideMapper<CategoryResponseDTO, CategoryBO>
): ICategoryRepository {
    override suspend fun findAll(): List<CategoryBO> = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }
}