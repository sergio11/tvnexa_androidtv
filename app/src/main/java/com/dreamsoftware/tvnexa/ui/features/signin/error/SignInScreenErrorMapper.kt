package com.dreamsoftware.tvnexa.ui.features.signin.error

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.utils.IErrorMapper

class SignInScreenErrorMapper(
    private val context: Context
): IErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is DomainException.InvalidSigInDataException -> R.string.sign_in_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}