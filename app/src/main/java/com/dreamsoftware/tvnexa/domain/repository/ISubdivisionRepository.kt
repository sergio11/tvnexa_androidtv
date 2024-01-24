package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import kotlin.jvm.Throws

interface ISubdivisionRepository {

    @Throws(
        DomainException.NoSubdivisionFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findAll(): List<SubdivisionBO>
}