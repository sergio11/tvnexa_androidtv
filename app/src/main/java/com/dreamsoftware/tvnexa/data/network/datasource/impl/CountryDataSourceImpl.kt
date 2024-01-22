package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.ICountryService

internal class CountryDataSourceImpl(
    private val countryService: ICountryService
): SupportNetworkDataSource(), ICountryDataSource {

    @Throws(NetworkException::class)
    override suspend fun findAll(): List<CountryResponseDTO> = safeNetworkCall {
        countryService.all().data
    }
}