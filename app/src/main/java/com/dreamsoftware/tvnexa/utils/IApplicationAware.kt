package com.dreamsoftware.tvnexa.utils

import androidx.annotation.StringRes

interface IApplicationAware {

    fun getString(@StringRes stringResId: Int): String
}