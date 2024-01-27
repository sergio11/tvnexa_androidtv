package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the structure of a generic API response.
 *
 * @property code The HTTP status code of the API response.
 * @property message A human-readable message associated with the API response.
 * @property data The data payload of the API response, generic type [T].
 *
 * @param T The type of the data payload in the API response.
 */
data class ApiResponseDTO<T>(
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String,
    @Json(name = "data")
    val data: T
)
