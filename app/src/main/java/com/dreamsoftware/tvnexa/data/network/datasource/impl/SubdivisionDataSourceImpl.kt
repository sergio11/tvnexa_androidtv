package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.ISubdivisionService

internal class SubdivisionDataSourceImpl(
    private val subdivisionService: ISubdivisionService
): SupportNetworkDataSource(), ISubdivisionDataSource {

    @Throws(NetworkException::class)
    override suspend fun findAll(): List<SubdivisionResponseDTO> = safeNetworkCall {
        subdivisionService.all().data
    }
}