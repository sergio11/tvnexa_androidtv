package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import kotlin.jvm.Throws

/**
 * Interface defining methods to interact with channel data.
 */
interface IChannelRepository {

    /**
     * Finds channels based on optional category, country, offset, and limit.
     *
     * @param category Optional category to filter channels.
     * @param country Optional country to filter channels.
     * @param offset The offset for pagination.
     * @param limit The limit for the number of channels to retrieve.
     * @return A list of SimpleChannelBO representing channels based on the specified criteria.
     * @throws DomainException.NoChannelsFoundException if no channels are found based on the criteria.
     * @throws DomainException.InternalErrorException if there is an internal error during the operation.
     */
    @Throws(
        DomainException.NoChannelsFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findBy(
        category: String? = null,
        country: String? = null,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO>

    /**
     * Finds the detailed information of a channel by its ID.
     *
     * @param channelId The unique identifier of the channel.
     * @return A ChannelDetailBO representing the detailed information of the channel.
     * @throws DomainException.ChannelNotFoundException if the channel with the given ID is not found.
     * @throws DomainException.InternalErrorException if there is an internal error during the operation.
     */
    @Throws(
        DomainException.ChannelNotFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findDetailById(channelId: String): ChannelDetailBO

    /**
     * Finds channels based on the specified search term.
     *
     * @param term The search term to filter channels.
     * @return A list of SimpleChannelBO representing channels matching the search term.
     * @throws DomainException.NoChannelsFoundException if no channels are found by the given term.
     * @throws DomainException.InternalErrorException if there is an internal error during the operation.
     */
    @Throws(
        DomainException.NoChannelsFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findByTerm(term: String): List<SimpleChannelBO>
}