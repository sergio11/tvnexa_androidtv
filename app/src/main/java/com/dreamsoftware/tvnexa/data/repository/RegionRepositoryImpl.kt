package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IRegionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.domain.repository.IRegionRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of the [IRegionRepository] interface responsible for handling region-related data operations.
 *
 * This class utilizes an instance of [IRegionDataSource] for fetching region data from the data source and
 * [IOneSideMapper] for mapping the response DTOs to business objects (BO).
 *
 * @property regionDataSource An instance of [IRegionDataSource] used for retrieving region data.
 * @property regionMapper An instance of [IOneSideMapper] responsible for mapping [RegionResponseDTO] to [RegionBO].
 */
internal class RegionRepositoryImpl(
    private val regionDataSource: IRegionDataSource,
    private val regionMapper: IOneSideMapper<RegionResponseDTO, RegionBO>
): SupportRepositoryImpl(), IRegionRepository {

    /**
     * Retrieves all regions.
     *
     * @return A list of [RegionBO] representing all regions.
     * @throws DomainException.NoRegionsFoundException if no regions are found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoRegionsFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<RegionBO> = safeExecute {
        regionDataSource.findAll().let(regionMapper::mapInListToOutList).toList().also {
            if (it.isEmpty()) {
                throw DomainException.NoRegionsFoundException("No regions found")
            }
        }
    }
}