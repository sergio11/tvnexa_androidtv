package com.dreamsoftware.tvnexa.ui.features.signin.error

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.ui.core.ISimpleErrorMapper

class SignInScreenSimpleErrorMapper(
    private val context: Context
): ISimpleErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is DomainException.InvalidDataException -> R.string.generic_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}