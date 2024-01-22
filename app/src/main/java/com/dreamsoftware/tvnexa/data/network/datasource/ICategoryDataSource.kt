package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving category information.
 */
interface ICategoryDataSource {

    /**
     * Fetches a list of all available categories.
     *
     * @return A list of [CategoryResponseDTO] representing categories.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findAll(): List<CategoryResponseDTO>
}