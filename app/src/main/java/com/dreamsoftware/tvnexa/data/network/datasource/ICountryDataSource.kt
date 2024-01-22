package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving country information.
 */
interface ICountryDataSource {

    /**
     * Fetches a list of all available countries.
     *
     * @return A list of [CountryResponseDTO] representing countries.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findAll(): List<CountryResponseDTO>
}