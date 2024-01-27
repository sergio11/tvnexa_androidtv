package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class RegionResponseDTO(
    // The code or identifier of the region.
    @Json(name = "code")
    val code: String,
    // The name or title of the region.
    @Json(name = "name")
    val name: String,
    // The list of countries within the region.
    @Json(name = "countries")
    val countries: Iterable<CountryResponseDTO>
)
