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

internal class ChannelRepositoryImpl(
    private val channelsDataSource: IChannelsDataSource,
    private val simpleChannelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
    private val channelMapper: IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>
): SupportRepositoryImpl(), IChannelRepository {

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
        channelsDataSource.findBy(category, country, offset, limit).let(simpleChannelMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoChannelsFoundException("No channels found")
            }
        }
    }

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
}