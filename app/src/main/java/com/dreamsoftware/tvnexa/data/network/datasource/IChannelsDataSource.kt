package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * DataSource interface for retrieving channel information.
 */
interface IChannelsDataSource {

    /**
     * Fetches a list of channels based on specified parameters.
     *
     * @param category The category of channels to filter by (optional).
     * @param country The country of channels to filter by (optional).
     * @param offset The offset for paginated requests.
     * @param limit The page size for paginated requests.
     * @return A list of [SimpleChannelResponseDTO] representing channels.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findBy(
        category: String? = null,
        country: String? = null,
        offset: Long,
        limit: Long
    ): List<SimpleChannelResponseDTO>

    /**
     * Fetches detailed information for a specific channel.
     *
     * @param channelId The unique identifier of the channel.
     * @return A [ChannelDetailResponseDTO] representing detailed information about the channel.
     * @throws NetworkException if there is a network-related error during the data retrieval.
     */
    @Throws(NetworkException::class)
    suspend fun findDetailById(channelId: String): ChannelDetailResponseDTO

    /**
     * Finds channels based on the specified search term.
     *
     * @param term The search term to filter channels.
     * @return A list of SimpleChannelResponseDTO matching the search term.
     * @throws NetworkException if there is a network-related issue during the operation.
     */
    @Throws(NetworkException::class)
    suspend fun findByTerm(term: String): List<SimpleChannelResponseDTO>
}