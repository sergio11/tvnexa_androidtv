package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IRegionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.IRegionService

internal class RegionDataSourceImpl(
    private val regionService: IRegionService
): SupportNetworkDataSource(), IRegionDataSource {

    @Throws(NetworkException::class)
    override suspend fun findAll(): List<RegionResponseDTO> = safeNetworkCall {
        regionService.all().data
    }
}