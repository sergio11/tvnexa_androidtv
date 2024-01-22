package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving subdivision information.
 */
interface ISubdivisionDataSource {

    /**
     * Fetches a list of all available subdivisions.
     *
     * @return A list of [SubdivisionResponseDTO] representing subdivisions.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findAll(): List<SubdivisionResponseDTO>
}