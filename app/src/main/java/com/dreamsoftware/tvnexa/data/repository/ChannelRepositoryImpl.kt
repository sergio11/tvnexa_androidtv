package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ChannelRepositoryImpl(
    private val channelsDataSource: IChannelsDataSource,
    private val simpleChannelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
    private val channelMapper: IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>
): IChannelRepository {

    override suspend fun findBy(
        category: String?,
        country: String?,
        offset: Long,
        limit: Long
    ): List<SimpleChannelBO> = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    override suspend fun findDetailById(channelId: String): ChannelDetailBO = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }
}