package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class CategoryResponseDTO(
    // The unique identifier of the category.
    @field:Json(name = "id")
    val id: String,
    // The name or title of the category.
    @field:Json(name = "name")
    val name: String
)
