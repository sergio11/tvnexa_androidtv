package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetCountriesUseCase(
    private val countryRepository: ICountryRepository
) : BaseUseCase<List<CountryBO>>() {

    override suspend fun onExecuted(): List<CountryBO> =
        countryRepository.findAll()
}