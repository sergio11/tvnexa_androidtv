package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving Electronic Program Guide (EPG) information.
 */
interface IEpgDataSource {

    /**
     * Fetches a list of channel programmes based on specified parameters.
     *
     * @param channelId The unique identifier of the channel.
     * @param startAt The start date and time for filtering programmes (optional).
     * @param endAt The end date and time for filtering programmes (optional).
     * @return A list of [EpgChannelProgrammeResponseDTO] representing channel programmes.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findChannelProgrammes(
        channelId: String,
        startAt: String? = null,
        endAt: String? = null
    ): List<EpgChannelProgrammeResponseDTO>

    /**
     * Fetches a list of country programmes based on specified parameters.
     *
     * @param channelId The unique identifier of the channel.
     * @param startAt The start date and time for filtering programmes (optional).
     * @param endAt The end date and time for filtering programmes (optional).
     * @return A list of [EpgChannelProgrammeResponseDTO] representing country programmes.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findCountryProgrammes(
        channelId: String,
        startAt: String? = null,
        endAt: String? = null
    ): List<EpgChannelProgrammeResponseDTO>
}