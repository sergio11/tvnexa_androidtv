package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import retrofit2.http.GET

/**
 * Service interface for interacting with the Country API.
 */
interface ICountryService {

    /**
     * Fetches a list of all available countries.
     *
     * @return A [ApiResponseDTO] containing a list of [CountryResponseDTO].
     */
    @GET("/countries/")
    suspend fun all(): ApiResponseDTO<List<CountryResponseDTO>>
}