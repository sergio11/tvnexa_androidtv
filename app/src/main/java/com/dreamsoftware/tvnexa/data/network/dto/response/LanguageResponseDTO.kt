package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class LanguageResponseDTO(
    // The language code (e.g., "en" for English).
    @field:Json(name = "code")
    val code: String,
    // The name of the language.
    @field:Json(name = "name")
    val name: String
)
