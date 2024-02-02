package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of the [ICategoryRepository] interface responsible for handling category-related data operations.
 *
 * This class utilizes an instance of [ICategoryDataSource] for fetching category data from the data source and
 * an [IOneSideMapper] for mapping the response DTOs to business objects (BO).
 *
 * @property categoryDataSource An instance of [ICategoryDataSource] used for retrieving category data.
 * @property categoryMapper An instance of [IOneSideMapper] responsible for mapping [CategoryResponseDTO] to [CategoryBO].
 */
internal class CategoryRepositoryImpl(
    private val categoryDataSource: ICategoryDataSource,
    private val categoryMapper: IOneSideMapper<CategoryResponseDTO, CategoryBO>
): SupportRepositoryImpl(), ICategoryRepository {

    /**
     * Retrieves all categories.
     *
     * @return A list of [CategoryBO] representing all categories.
     * @throws DomainException.NoCategoriesFoundException if no categories are found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoCategoriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<CategoryBO> = safeExecute {
        // Fetches category data from the data source, maps the response, and handles exceptions.
        categoryDataSource.findAll().let(categoryMapper::mapInListToOutList).toList().also {
            if (it.isEmpty()) {
                throw DomainException.NoCategoriesFoundException("No categories found")
            }
        }
    }
}