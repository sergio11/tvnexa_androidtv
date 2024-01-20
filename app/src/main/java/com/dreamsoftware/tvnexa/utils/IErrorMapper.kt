package com.dreamsoftware.tvnexa.utils

interface IErrorMapper {
    fun mapToMessage(ex: Throwable): String
}