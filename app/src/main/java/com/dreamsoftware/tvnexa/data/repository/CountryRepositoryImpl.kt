package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

internal class CountryRepositoryImpl(
    private val countryDataSource: ICountryDataSource,
    private val countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>
): SupportRepositoryImpl(), ICountryRepository {

    @Throws(
        DomainException.NoCountriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<CountryBO> = safeExecute {
        countryDataSource.findAll().let(countryMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoCountriesFoundException("No countries found")
            }
        }
    }
}