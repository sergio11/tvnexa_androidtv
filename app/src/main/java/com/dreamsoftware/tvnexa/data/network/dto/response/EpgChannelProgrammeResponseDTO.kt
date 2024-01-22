package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class EpgChannelProgrammeResponseDTO(
    // The unique identifier of the program.
    @field:Json(name = "id")
    val id: Long,
    // The title or name of the program.
    @field:Json(name = "title")
    val title: String,
    // The channel where the program is broadcast.
    @field:Json(name = "channel")
    val channel: SimpleChannelResponseDTO?,
    // The category or genre of the program (if available).
    @field:Json(name = "category")
    val category: CategoryResponseDTO?,
    // The start date and time of the program.
    @field:Json(name = "start")
    val start: String,
    // The end date and time of the program.
    @field:Json(name = "end")
    val end: String,
    // The date and time when the program will air.
    @field:Json(name = "date")
    val date: String
)
