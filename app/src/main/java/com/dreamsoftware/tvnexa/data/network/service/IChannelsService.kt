package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service interface for interacting with the Channels API.
 */
interface IChannelsService {

    /**
     * Companion object containing default values for page size and offset.
     */
    companion object {
        /**
         * Default page size for paginated requests.
         */
        const val DEFAULT_PAGE_SIZE = 100L

        /**
         * Default offset for paginated requests.
         */
        const val DEFAULT_OFFSET = 0L
    }

    /**
     * Fetches a list of channels based on specified parameters.
     *
     * @param category The category of channels to filter by.
     * @param country The country of channels to filter by.
     * @param offset The offset for paginated requests (default is [DEFAULT_OFFSET]).
     * @param limit The page size for paginated requests (default is [DEFAULT_PAGE_SIZE]).
     * @return A [ApiResponseDTO] containing a list of [SimpleChannelResponseDTO].
     */
    @GET("channels/")
    suspend fun findBy(
        @Query("category") category: String? = null,
        @Query("country") country: String? = null,
        @Query("offset") offset: Long = DEFAULT_OFFSET,
        @Query("limit") limit: Long = DEFAULT_PAGE_SIZE
    ): ApiResponseDTO<List<SimpleChannelResponseDTO>>

    /**
     * Fetches detailed information for a specific channel.
     *
     * @param channelId The unique identifier of the channel.
     * @return A [ApiResponseDTO] containing detailed information about the channel.
     */
    @GET("channels/{channelId}")
    suspend fun detail(
        @Path("channelId") channelId: String
    ): ApiResponseDTO<ChannelDetailResponseDTO>

    /**
     * Performs a search for channels based on the specified term.
     *
     * @param term The search term to look for in channel names.
     * @return ApiResponseDTO containing a list of SimpleChannelResponseDTO matching the search term.
     */
    @GET("channels/search")
    suspend fun search(
        @Query("term") term: String
    ): ApiResponseDTO<List<SimpleChannelResponseDTO>>
}