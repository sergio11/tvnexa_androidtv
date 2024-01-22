package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IEpgDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.IEpgService

internal class EpgDataSourceImpl(
    private val epgService: IEpgService
): SupportNetworkDataSource(), IEpgDataSource {

    @Throws(NetworkException::class)
    override suspend fun findChannelProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeResponseDTO> = safeNetworkCall {
        epgService.findChannelProgrammes(channelId, startAt, endAt).data
    }

    @Throws(NetworkException::class)
    override suspend fun findCountryProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeResponseDTO> = safeNetworkCall {
        epgService.findCountryProgrammes(channelId, startAt, endAt).data
    }
}