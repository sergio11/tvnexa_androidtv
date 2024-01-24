package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

internal class CategoryRepositoryImpl(
    private val categoryDataSource: ICategoryDataSource,
    private val categoryMapper: IOneSideMapper<CategoryResponseDTO, CategoryBO>
): SupportRepositoryImpl(), ICategoryRepository {

    @Throws(
        DomainException.NoCategoriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<CategoryBO> = safeExecute {
        categoryDataSource.findAll().let(categoryMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoCategoriesFoundException("No categories found")
            }
        }
    }
}