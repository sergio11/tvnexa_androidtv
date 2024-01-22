package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class SubdivisionResponseDTO(
    // The code or identifier of the subdivision.
    @field:Json(name = "code")
    val code: String,
    // The country to which the subdivision belongs.
    @field:Json(name = "country")
    val country: CountryResponseDTO,
    // The name or title of the subdivision.
    @field:Json(name = "name")
    val name: String
)
