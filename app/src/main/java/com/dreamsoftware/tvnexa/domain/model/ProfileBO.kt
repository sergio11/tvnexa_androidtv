package com.dreamsoftware.tvnexa.domain.model

data class ProfileBO(
    val uuid: String,
    val name: String,
    val type: ProfileTypeEnum
)

enum class ProfileTypeEnum {
    BOY, GIRL, WOMAN, MAN
}
