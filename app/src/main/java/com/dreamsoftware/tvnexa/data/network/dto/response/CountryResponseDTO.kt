package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class CountryResponseDTO(
    // The country code (e.g., "US" for United States).
    @field:Json(name = "code")
    val code: String,
    // The name of the country.
    @field:Json(name = "name")
    val name: String,
    // The URL to the flag image of the country (if available).
    @field:Json(name = "flag")
    val flag: String? = null,
    // The list of languages spoken in the country.
    @field:Json(name = "languages")
    val languages: List<LanguageResponseDTO>
)
