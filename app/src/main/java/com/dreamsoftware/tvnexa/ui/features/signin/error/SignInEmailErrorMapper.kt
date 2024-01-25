package com.dreamsoftware.tvnexa.ui.features.signin.error

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.exception.FormFieldKey
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.utils.IErrorMapper

class SignInEmailErrorMapper(
    private val context: Context
): IErrorMapper {
    override fun mapToMessage(ex: Throwable): String = with(context) {
        if(ex is DomainException.InvalidSigInDataException && ex.field == FormFieldKey.EMAIL) {
            if(ex.value.isNullOrBlank()) {
                getString(R.string.sign_in_form_empty_email_address)
            } else {
                getString(R.string.sign_in_form_invalid_email_address, ex.value)
            }
        } else {
            String.EMPTY
        }
    }
}