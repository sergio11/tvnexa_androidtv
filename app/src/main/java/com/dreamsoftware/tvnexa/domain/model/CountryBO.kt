package com.dreamsoftware.tvnexa.domain.model

data class CountryBO(
    val code: String,
    val name: String,
    val flag: String?,
    val languages: List<LanguageBO>
)
