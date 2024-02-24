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
     * Finds channels for a specific user profile based on the given criteria and NSFW filter.
     *
     * @param profileId The ID of the user profile.
     * @param enableNsfw A flag indicating whether to include NSFW channels in the search results.
     * @param category The category of channels to search for (optional).
     * @param country The country of channels to search for (optional).
     * @param offset The offset for pagination.
     * @param limit The maximum number of channels to retrieve.
     * @return A list of [SimpleChannelBO] objects representing the found channels.
     * @throws DomainException.NoChannelsFoundException if no channels are found based on the criteria and filter.
     * @throws DomainException.InternalErrorException if there's an internal error while processing the request.
     */
    @Throws(
        DomainException.NoChannelsFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findBy(
        profileId: String,
        enableNsfw: Boolean,
        category: String? = null,
        country: String? = null,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO>

    /**
     * Finds the details of a channel for a specific user profile by its ID with NSFW filter option.
     *
     * @param profileId The ID of the user profile.
     * @param enableNsfw A flag indicating whether to include NSFW content in the channel details.
     * @param channelId The ID of the channel to find details for.
     * @return A [ChannelDetailBO] object representing the details of the found channel.
     * @throws DomainException.ChannelNotFoundException if the channel is not found.
     * @throws DomainException.InternalErrorException if there's an internal error while processing the request.
     */
    @Throws(
        DomainException.ChannelNotFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findDetailById(
        profileId: String,
        enableNsfw: Boolean,
        channelId: String
    ): ChannelDetailBO

    /**
     * Finds channels for a specific user profile based on the given search term and NSFW filter.
     *
     * @param profileId The ID of the user profile.
     * @param enableNsfw A flag indicating whether to include NSFW channels in the search results.
     * @param term The search term to look for in channel names.
     * @return A list of [SimpleChannelBO] objects representing the found channels.
     * @throws DomainException.NoChannelsFoundException if no channels are found based on the search term and filter.
     * @throws DomainException.InternalErrorException if there's an internal error while processing the request.
     */
    @Throws(
        DomainException.NoChannelsFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findByTerm(
        profileId: String,
        enableNsfw: Boolean,
        term: String
    ): List<SimpleChannelBO>


    /**
     * Blocks a channel for a specific user profile.
     *
     * @param profileId The ID of the user profile.
     * @param channelId The ID of the channel to be blocked.
     * @throws DomainException.InternalErrorException if there's an internal error while processing the request.
     * @throws DomainException.BlockChannelErrorException if there's an error while blocking the channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.BlockChannelErrorException::class
    )
    suspend fun blockChannel(profileId: String, channelId: String)

    /**
     * Unblocks a channel for a specific user profile.
     *
     * @param profileId The ID of the user profile.
     * @param channelId The ID of the channel to be unblocked.
     * @throws DomainException.InternalErrorException if there's an internal error while processing the request.
     * @throws DomainException.UnblockChannelErrorException if there's an error while unblocking the channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.UnblockChannelErrorException::class
    )
    suspend fun unblockChannel(profileId: String, channelId: String)

    /**
     * Retrieves the list of favorite channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of favorite channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO>

    /**
     * Saves a channel as a favorite for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be saved as a favorite.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.SaveFavoriteChannelErrorException if an error occurs while saving the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.SaveFavoriteChannelErrorException::class
    )
    suspend fun saveFavoriteChannels(profileId: String, channelId: String)

    /**
     * Deletes a channel from the list of favorites for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be deleted from favorites.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.DeleteFavoriteChannelErrorException if an error occurs while deleting the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.DeleteFavoriteChannelErrorException::class
    )
    suspend fun deleteFavoriteChannels(profileId: String, channelId: String)
}