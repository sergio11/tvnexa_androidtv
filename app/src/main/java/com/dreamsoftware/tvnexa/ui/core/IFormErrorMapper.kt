package com.dreamsoftware.tvnexa.ui.core

import com.dreamsoftware.tvnexa.domain.model.FormFieldKey

interface IFormErrorMapper {
    fun mapToMessage(key: FormFieldKey, ex: Throwable): String
}