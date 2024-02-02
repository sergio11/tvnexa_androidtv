package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of the [ICountryRepository] interface responsible for handling country-related data operations.
 *
 * This class utilizes an instance of [ICountryDataSource] for fetching country data from the data source and
 * [IOneSideMapper] for mapping the response DTOs to business objects (BO).
 *
 * @property countryDataSource An instance of [ICountryDataSource] used for retrieving country data.
 * @property countryMapper An instance of [IOneSideMapper] responsible for mapping [CountryResponseDTO] to [CountryBO].
 */
internal class CountryRepositoryImpl(
    private val countryDataSource: ICountryDataSource,
    private val countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>
): SupportRepositoryImpl(), ICountryRepository {

    /**
     * Retrieves all countries.
     *
     * @return A list of [CountryBO] representing all countries.
     * @throws DomainException.NoCountriesFoundException if no countries are found.
     * @throws DomainException.InternalErrorException if there is an internal error during execution.
     */
    @Throws(
        DomainException.NoCountriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<CountryBO> = safeExecute {
        countryDataSource.findAll().let(countryMapper::mapInListToOutList).toList().also {
            if (it.isEmpty()) {
                throw DomainException.NoCountriesFoundException("No countries found")
            }
        }
    }
}