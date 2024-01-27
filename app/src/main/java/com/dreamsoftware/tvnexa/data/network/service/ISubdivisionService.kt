package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import retrofit2.http.GET

/**
 * Service interface for interacting with the Subdivision API.
 */
interface ISubdivisionService {

    /**
     * Fetches a list of all available subdivisions.
     *
     * @return A [ApiResponseDTO] containing a list of [SubdivisionResponseDTO].
     */
    @GET("subdivisions/")
    suspend fun all(): ApiResponseDTO<List<SubdivisionResponseDTO>>
}