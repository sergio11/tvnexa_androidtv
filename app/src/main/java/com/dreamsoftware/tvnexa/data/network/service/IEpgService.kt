package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service interface for interacting with the Electronic Program Guide (EPG) API.
 */
interface IEpgService {

    /**
     * Fetches a list of channel programmes based on the specified parameters.
     *
     * @param channelId The unique identifier of the channel.
     * @param startAt The start date and time for filtering programmes (optional).
     * @param endAt The end date and time for filtering programmes (optional).
     * @return A [ApiResponseDTO] containing a list of [EpgChannelProgrammeResponseDTO].
     */
    @GET("/channel-programmes/{channelId}")
    suspend fun findChannelProgrammes(
        @Path("channelId") channelId: String,
        @Query("startAt") startAt: String? = null,
        @Query("endAt") endAt: String? = null
    ): ApiResponseDTO<List<EpgChannelProgrammeResponseDTO>>

    /**
     * Fetches a list of country programmes based on the specified parameters.
     *
     * @param countryCode The country code for filtering programmes.
     * @param startAt The start date and time for filtering programmes (optional).
     * @param endAt The end date and time for filtering programmes (optional).
     * @return A [ApiResponseDTO] containing a list of [EpgChannelProgrammeResponseDTO].
     */
    @GET("/country-programmes/{countryCode}")
    suspend fun findCountryProgrammes(
        @Path("channelId") channelId: String,
        @Query("startAt") startAt: String? = null,
        @Query("endAt") endAt: String? = null
    ): ApiResponseDTO<List<EpgChannelProgrammeResponseDTO>>
}