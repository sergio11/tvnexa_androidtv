package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IRegionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.domain.repository.IRegionRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

internal class RegionRepositoryImpl(
    private val regionDataSource: IRegionDataSource,
    private val regionMapper: IOneSideMapper<RegionResponseDTO, RegionBO>
): SupportRepositoryImpl(), IRegionRepository {

    @Throws(
        DomainException.NoRegionsFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<RegionBO> = safeExecute {
        regionDataSource.findAll().let(regionMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoRegionsFoundException("No regions found")
            }
        }
    }
}