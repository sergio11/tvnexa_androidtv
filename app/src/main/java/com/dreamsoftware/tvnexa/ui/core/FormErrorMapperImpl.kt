package com.dreamsoftware.tvnexa.ui.core

import android.content.Context
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY

class FormErrorMapperImpl(
    private val context: Context
): IFormErrorMapper {

    override fun mapToMessage(key: FormFieldKey, ex: Throwable): String = with(context) {
        if(ex is DomainException.InvalidDataException && key == ex.field) {
            when(ex.field) {
                FormFieldKey.EMAIL -> if(ex.value.isNullOrBlank()) {
                    getString(R.string.account_form_empty_email_address)
                } else {
                    getString(R.string.account_form_invalid_email_address, ex.value)
                }
                FormFieldKey.PASSWORD -> getString(
                    if(ex.value.isNullOrBlank()) {
                        R.string.account_form_empty_password
                    } else {
                        R.string.account_form_invalid_password
                    }
                )
                FormFieldKey.USERNAME -> getString(
                    if(ex.value.isNullOrBlank()) {
                        R.string.account_form_empty_username
                    } else {
                        R.string.account_form_invalid_username
                    }
                )
                FormFieldKey.FIRST_NAME -> getString(R.string.account_form_invalid_firstname)
                FormFieldKey.LAST_NAME -> getString(R.string.account_form_invalid_lastname)
            }
        } else {
            String.EMPTY
        }
    }
}