package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving region information.
 */
interface IRegionDataSource {

    /**
     * Fetches a list of all available regions.
     *
     * @return A list of [RegionResponseDTO] representing regions.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findAll(): List<RegionResponseDTO>
}