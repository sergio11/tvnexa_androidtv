package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CountryRepositoryImpl(
    private val countryDataSource: ICountryDataSource,
    private val countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>
): ICountryRepository {
    override suspend fun findAll(): List<CountryBO> = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }
}