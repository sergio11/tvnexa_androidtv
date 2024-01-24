package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.model.CountryBO

interface ICountryRepository {

    suspend fun findAll(): List<CountryBO>
}