package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import kotlin.jvm.Throws

interface ICountryRepository {

    @Throws(
        DomainException.NoCountriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findAll(): List<CountryBO>
}