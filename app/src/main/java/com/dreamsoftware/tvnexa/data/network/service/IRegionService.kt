package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import retrofit2.http.GET

/**
 * Service interface for interacting with the Region API.
 */
interface IRegionService {

    /**
     * Fetches a list of all available regions.
     *
     * @return A [ApiResponseDTO] containing a list of [RegionResponseDTO].
     */
    @GET("/regions/")
    suspend fun all(): ApiResponseDTO<List<RegionResponseDTO>>
}