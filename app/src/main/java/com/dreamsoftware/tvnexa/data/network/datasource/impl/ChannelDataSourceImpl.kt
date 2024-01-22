package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.IChannelsService

internal class ChannelDataSourceImpl(
    private val channelService: IChannelsService
): SupportNetworkDataSource(), IChannelsDataSource {

    @Throws(NetworkException::class)
    override suspend fun findBy(
        category: String?,
        country: String?,
        offset: Long,
        limit: Long
    ): List<SimpleChannelResponseDTO> = safeNetworkCall {
        channelService.findBy(category, country, offset, limit).data
    }

    @Throws(NetworkException::class)
    override suspend fun findDetailById(channelId: String): ChannelDetailResponseDTO = safeNetworkCall {
        channelService.detail(channelId).data
    }
}