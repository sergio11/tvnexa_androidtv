package com.dreamsoftware.tvnexa.ui.features.signin.error

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.exception.FormFieldKey
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.utils.IErrorMapper

class SignInPasswordErrorMapper(
    private val context: Context
): IErrorMapper {
    override fun mapToMessage(ex: Throwable): String = with(context) {
        if(ex is DomainException.InvalidSigInDataException && ex.field == FormFieldKey.PASSWORD) {
            getString(
                if(ex.value.isNullOrBlank()) {
                   R.string.sign_in_form_empty_password
                } else {
                    R.string.sign_in_form_invalid_password
                }
            )
        } else {
            String.EMPTY
        }
    }
}