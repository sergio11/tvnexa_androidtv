package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import retrofit2.http.GET

/**
 * Service interface for interacting with the Category API.
 */
interface ICategoryService {

    /**
     * Fetches a list of all available categories.
     *
     * @return A [ApiResponseDTO] containing a list of [CategoryResponseDTO].
     */
    @GET("categories/")
    suspend fun all(): ApiResponseDTO<List<CategoryResponseDTO>>
}