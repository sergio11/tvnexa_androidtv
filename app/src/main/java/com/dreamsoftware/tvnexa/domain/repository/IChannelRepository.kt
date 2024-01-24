package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO

interface IChannelRepository {

    suspend fun findBy(
        category: String? = null,
        country: String? = null,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO>

    suspend fun findDetailById(channelId: String): ChannelDetailBO
}