package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import kotlin.jvm.Throws

interface IChannelRepository {

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

    @Throws(
        DomainException.ChannelNotFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findDetailById(channelId: String): ChannelDetailBO
}