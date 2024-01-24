package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import kotlin.jvm.Throws

interface IRegionRepository {

    @Throws(
        DomainException.NoRegionsFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findAll(): List<RegionBO>
}