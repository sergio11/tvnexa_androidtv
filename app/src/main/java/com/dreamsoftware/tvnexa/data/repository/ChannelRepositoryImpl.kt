package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IUserDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of the [IChannelRepository] interface responsible for handling channel-related data operations.
 *
 * This class utilizes an instance of [IChannelsDataSource] for fetching channel data from the data source and
 * [IOneSideMapper] instances for mapping the response DTOs to business objects (BO).
 *
 * @property channelsDataSource An instance of [IChannelsDataSource] used for retrieving channel data.
 * @property simpleChannelMapper An instance of [IOneSideMapper] responsible for mapping [SimpleChannelResponseDTO] to [SimpleChannelBO].
 * @property channelMapper An instance of [IOneSideMapper] responsible for mapping [ChannelDetailResponseDTO] to [ChannelDetailBO].
 */
internal class ChannelRepositoryImpl(
    private val channelsDataSource: IChannelsDataSource,
    private val userDataSource: IUserDataSource,
    private val simpleChannelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
    private val channelMapper: IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>
): SupportRepositoryImpl(), IChannelRepository {

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
    override suspend fun findBy(
        profileId: String,
        enableNsfw: Boolean,
        category: String?,
        country: String?,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO> = safeExecute {
        val blockChannelsByProfile = userDataSource.getBlockedChannels(profileId)
        channelsDataSource.findBy(category, country, offset, limit)
            .let(simpleChannelMapper::mapInListToOutList)
            .map { it.copy(isBlocked = isChannelBlocked(
                blockedChannels = blockChannelsByProfile,
                channelId = it.channelId,
                channelIsNsfw = it.isNsfw == true,
                enableNsfw = enableNsfw
            )) }
            .toList().also {
                if (it.isEmpty()) {
                    throw DomainException.NoChannelsFoundException("No channels found")
                }
            }
    }

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
    override suspend fun findDetailById(
        profileId: String,
        enableNsfw: Boolean,
        channelId: String
    ): ChannelDetailBO = safeExecute {
        try {
            channelsDataSource.findDetailById(channelId).let(channelMapper::mapInToOut).let {
                it.copy(isBlocked = isChannelBlocked(
                    blockedChannels = userDataSource.getBlockedChannels(profileId),
                    channelId = it.channelId,
                    channelIsNsfw = it.isNsfw == true,
                    enableNsfw = enableNsfw
                ))
            }
        } catch (ex: NetworkException.NoResultException) {
            throw DomainException.ChannelNotFoundException("Channel $channelId not found", ex)
        }
    }

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
    override suspend fun findByTerm(
        profileId: String,
        enableNsfw: Boolean,
        term: String
    ): List<SimpleChannelBO> = safeExecute {
        try {
            val blockedChannelsByProfile = userDataSource.getBlockedChannels(profileId)
            channelsDataSource.findByTerm(term)
                .let(simpleChannelMapper::mapInListToOutList)
                .map { it.copy(isBlocked = isChannelBlocked(
                    blockedChannels = blockedChannelsByProfile,
                    channelId = it.channelId,
                    channelIsNsfw = it.isNsfw == true,
                    enableNsfw = enableNsfw
                ))}
                .toList()
        } catch (ex: NetworkException.NoResultException) {
            throw DomainException.NoChannelsFoundException("No channels found by `$term`", ex)
        }
    }

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
    override suspend fun blockChannel(
        profileId: String,
        channelId: String
    ) {
        safeExecute {
            userDataSource.blockChannel(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.BlockChannelErrorException("Channel $channelId could not be blocked")
                }
            }
        }
    }

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
    override suspend fun unblockChannel(
        profileId: String,
        channelId: String
    ) {
        safeExecute {
            userDataSource.unblockChannel(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.UnblockChannelErrorException("Channel $channelId could not be unblocked")
                }
            }
        }
    }

    /**
     * Retrieves the list of favorite channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of favorite channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO> = safeExecute {
        userDataSource.getFavoriteChannels(profileId)
            .let(simpleChannelMapper::mapInListToOutList)
            .toList()
    }

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
    override suspend fun saveFavoriteChannels(
        profileId: String,
        channelId: String
    ) {
        safeExecute {
            userDataSource.saveFavoriteChannels(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.SaveFavoriteChannelErrorException("Channel $channelId could not be saved as favorite")
                }
            }
        }
    }

    /**
     * Deletes a channel from the list of favorites for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be deleted from favorites.
     * @return true if the operation is successful, false otherwise.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.DeleteFavoriteChannelErrorException if an error occurs while deleting the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.DeleteFavoriteChannelErrorException::class
    )
    override suspend fun deleteFavoriteChannels(
        profileId: String,
        channelId: String
    ) {
        safeExecute {
            userDataSource.deleteFavoriteChannels(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.DeleteFavoriteChannelErrorException("Channel $channelId could not be deleted from favorites")
                }
            }
        }
    }

    private fun isChannelBlocked(
        blockedChannels: List<String>,
        channelId: String,
        channelIsNsfw: Boolean,
        enableNsfw: Boolean,
    ) =
        blockedChannels.contains(channelId) || channelIsNsfw && !enableNsfw
}