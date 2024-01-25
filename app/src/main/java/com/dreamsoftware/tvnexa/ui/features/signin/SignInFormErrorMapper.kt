package com.dreamsoftware.tvnexa.ui.features.signin

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.exception.FormFieldKey
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.utils.IErrorMapper

class SignInFormErrorMapper(
    private val context: Context
): IErrorMapper {
    override fun mapToMessage(ex: Throwable): String = with(context) {
        if(ex is DomainException.InvalidSigInDataException) {
            when(ex.field) {
                FormFieldKey.EMAIL -> getString(R.string.sign_in_form_invalid_email_address, ex.value)
                FormFieldKey.PASSWORD -> getString(R.string.sign_in_form_invalid_password)
                else -> String.EMPTY
            }
        } else {
            String.EMPTY
        }
    }
}