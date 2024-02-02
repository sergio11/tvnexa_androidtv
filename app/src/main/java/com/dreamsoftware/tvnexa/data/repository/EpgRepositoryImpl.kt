package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IEpgDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.EpgChannelProgrammeBO
import com.dreamsoftware.tvnexa.domain.repository.IEpgRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper

/**
 * Implementation of the [IEpgRepository] interface responsible for handling Electronic Program Guide (EPG) related data operations.
 *
 * This class utilizes an instance of [IEpgDataSource] for fetching EPG data from the data source and
 * [IOneSideMapper] for mapping the response DTOs to business objects (BO).
 *
 * @property epgDataSource An instance of [IEpgDataSource] used for retrieving EPG data.
 * @property epgMapper An instance of [IOneSideMapper] responsible for mapping [EpgChannelProgrammeResponseDTO] to [EpgChannelProgrammeBO].
 */
internal class EpgRepositoryImpl(
    private val epgDataSource: IEpgDataSource,
    private val epgMapper: IOneSideMapper<EpgChannelProgrammeResponseDTO, EpgChannelProgrammeBO>
): SupportRepositoryImpl(), IEpgRepository {

    /**
     * Retrieves Electronic Program Guide (EPG) data for a specific channel within a specified time range.
     *
     * @param channelId The ID of the channel for which EPG data is requested.
     * @param startAt The start time of the EPG data range (optional).
     * @param endAt The end time of the EPG data range (optional).
     * @return A list of [EpgChannelProgrammeBO] representing EPG data for the specified channel.
     * @throws DomainException.NoChannelProgrammesFoundException if no EPG data is found for the specified channel.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoChannelProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findChannelProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeBO> = safeExecute {
        epgDataSource.findChannelProgrammes(channelId, startAt, endAt)
            .let(epgMapper::mapInListToOutList)
            .toList().also {
                if (it.isEmpty()) {
                    throw DomainException.NoChannelProgrammesFoundException("No Channels programmes found")
                }
            }
    }

    /**
     * Retrieves Electronic Program Guide (EPG) data for a specific country within a specified time range.
     *
     * @param channelId The ID of the channel for which EPG data is requested.
     * @param startAt The start time of the EPG data range (optional).
     * @param endAt The end time of the EPG data range (optional).
     * @return A list of [EpgChannelProgrammeBO] representing EPG data for the specified country.
     * @throws DomainException.NoCountryProgrammesFoundException if no EPG data is found for the specified country.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoCountryProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findCountryProgrammes(
        channelId: String,
        startAt: String?,
        endAt: String?
    ): List<EpgChannelProgrammeBO> = safeExecute {
        epgDataSource.findCountryProgrammes(channelId, startAt, endAt)
            .let(epgMapper::mapInListToOutList)
            .toList().also {
                if (it.isEmpty()) {
                    throw DomainException.NoCountryProgrammesFoundException("No country programmes found")
                }
            }
    }
}