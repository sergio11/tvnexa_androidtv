package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.EpgChannelProgrammeBO
import kotlin.jvm.Throws

interface IEpgRepository {

    @Throws(
        DomainException.NoChannelProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findChannelProgrammes(
        channelId: String,
        startAt: String? = null,
        endAt: String? = null
    ): List<EpgChannelProgrammeBO>

    @Throws(
        DomainException.NoCountryProgrammesFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findCountryProgrammes(
        channelId: String,
        startAt: String? = null,
        endAt: String? = null
    ): List<EpgChannelProgrammeBO>
}