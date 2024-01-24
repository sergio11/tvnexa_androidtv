package com.dreamsoftware.tvnexa.domain.extensions

fun String.isEmailValid() =
    matches(Regex("^[a-zA-Z0-9_!#\$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$"))

fun String.isEmailNotValid() = !isEmailValid()

fun String.isPasswordValid(): Boolean {
    val minLength = 8
    return length >= minLength &&
            any { it.isLowerCase() } &&
            any { it.isUpperCase() } &&
            any { it.isDigit() } &&
            any { it in setOf('!', '@', '#', '$', '%', '^', '&', '*') }
}

fun String.isPasswordNotValid(): Boolean = !isPasswordValid()

fun String.isUsernameValid(): Boolean =
    length >= 5

fun String.isUsernameNotValid() = !isUsernameValid()

fun String.isFirstNameValid(): Boolean = isNotBlank()

fun String.isFirstNameNotValid() = !isFirstNameValid()

fun String.isLastNameValid(): Boolean = isNotBlank()

fun String.isLastNameNotValid() = !isLastNameValid()