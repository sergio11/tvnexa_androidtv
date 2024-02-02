package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.domain.repository.ISubdivisionRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of the [ISubdivisionRepository] interface responsible for handling subdivision-related data operations.
 *
 * This class utilizes an instance of [ISubdivisionDataSource] for fetching subdivision data from the data source and
 * [IOneSideMapper] for mapping the response DTOs to business objects (BO).
 *
 * @property subdivisionDataSource An instance of [ISubdivisionDataSource] used for retrieving subdivision data.
 * @property subdivisionMapper An instance of [IOneSideMapper] responsible for mapping [SubdivisionResponseDTO] to [SubdivisionBO].
 */
internal class SubdivisionRepositoryImpl(
    private val subdivisionDataSource: ISubdivisionDataSource,
    private val subdivisionMapper: IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>
): SupportRepositoryImpl(), ISubdivisionRepository {

    /**
     * Retrieves all subdivisions.
     *
     * @return A list of [SubdivisionBO] representing all subdivisions.
     * @throws DomainException.NoSubdivisionFoundException if no subdivisions are found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoSubdivisionFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<SubdivisionBO> = safeExecute {
        subdivisionDataSource.findAll().let(subdivisionMapper::mapInListToOutList).toList().also {
            if (it.isEmpty()) {
                throw DomainException.NoSubdivisionFoundException("No subdivision found")
            }
        }
    }
}