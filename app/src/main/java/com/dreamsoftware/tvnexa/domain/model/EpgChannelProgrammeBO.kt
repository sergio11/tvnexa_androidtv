package com.dreamsoftware.tvnexa.domain.model

data class EpgChannelProgrammeBO(
    val id: Long,
    val title: String,
    val channel: SimpleChannelBO?,
    val category: CategoryBO?,
    val start: String,
    val end: String,
    val date: String
)
