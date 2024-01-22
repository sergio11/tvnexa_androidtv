package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class RegionResponseDTO(
    // The code or identifier of the region.
    @field:Json(name = "code")
    val code: String,
    // The name or title of the region.
    @field:Json(name = "name")
    val name: String,
    // The list of countries within the region.
    @field:Json(name = "countries")
    val countries: Iterable<CountryResponseDTO>
)
