package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class LanguageResponseDTO(
    // The language code (e.g., "en" for English).
    @Json(name = "code")
    val code: String,
    // The name of the language.
    @Json(name = "name")
    val name: String
)
