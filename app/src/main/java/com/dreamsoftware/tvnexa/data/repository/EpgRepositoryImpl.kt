package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IEpgDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.EpgChannelProgrammeBO
import com.dreamsoftware.tvnexa.domain.repository.IEpgRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper

internal class EpgRepositoryImpl(
    private val epgDataSource: IEpgDataSource,
    private val epgMapper: IOneSideMapper<EpgChannelProgrammeResponseDTO, EpgChannelProgrammeBO>
): SupportRepositoryImpl(), IEpgRepository {

    @Throws(
        DomainException.NoChannelProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findChannelProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeBO> = safeExecute {
        epgDataSource.findChannelProgrammes(channelId, startAt, endAt).let(epgMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoChannelProgrammesFoundException("No Channels programmes found")
            }
        }
    }

    @Throws(
        DomainException.NoCountryProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findCountryProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeBO> = safeExecute {
        epgDataSource.findCountryProgrammes(channelId, startAt, endAt).let(epgMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoCountryProgrammesFoundException("No country programmes found")
            }
        }
    }
}
