package com.dreamsoftware.tvnexa.ui.core

interface ISimpleErrorMapper {
    fun mapToMessage(ex: Throwable): String
}