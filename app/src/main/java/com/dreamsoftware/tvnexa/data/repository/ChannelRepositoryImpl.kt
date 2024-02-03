package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
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
    private val simpleChannelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
    private val channelMapper: IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>
): SupportRepositoryImpl(), IChannelRepository {

    /**
     * Retrieves channels based on specified criteria.
     *
     * @param category The category filter for channels.
     * @param country The country filter for channels.
     * @param offset The offset for pagination.
     * @param limit The limit for the number of channels to retrieve.
     * @return A list of [SimpleChannelBO] representing channels based on the specified criteria.
     * @throws DomainException.NoChannelsFoundException if no channels are found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoChannelsFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findBy(
        category: String?,
        country: String?,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO> = safeExecute {
        channelsDataSource.findBy(category, country, offset, limit)
            .let(simpleChannelMapper::mapInListToOutList)
            .toList().also {
                if (it.isEmpty()) {
                    throw DomainException.NoChannelsFoundException("No channels found")
                }
            }
    }

    /**
     * Retrieves detailed information about a specific channel by its ID.
     *
     * @param channelId The ID of the channel to retrieve detailed information.
     * @return A [ChannelDetailBO] representing detailed information about the specified channel.
     * @throws DomainException.ChannelNotFoundException if the specified channel is not found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.ChannelNotFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findDetailById(channelId: String): ChannelDetailBO = safeExecute {
        try {
            channelsDataSource.findDetailById(channelId).let(channelMapper::mapInToOut)
        } catch (ex: NetworkException.NoResultException) {
            throw DomainException.ChannelNotFoundException("Channel $channelId not found", ex)
        }
    }

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
    override suspend fun findByTerm(term: String): List<SimpleChannelBO> = safeExecute {
        try {
            channelsDataSource.findByTerm(term)
                .let(simpleChannelMapper::mapInListToOutList)
                .toList()
        } catch (ex: NetworkException.NoResultException) {
            throw DomainException.NoChannelsFoundException("No channels found by `$term`", ex)
        }
    }
}