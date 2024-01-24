package com.dreamsoftware.tvnexa.domain.exception


sealed class DomainException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    data class NoRegionsFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoCountriesFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoCategoriesFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoSubdivisionFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoChannelsFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class ChannelNotFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoChannelProgrammesFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class NoCountryProgrammesFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class InvalidSessionException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class SigInFailedException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class InvalidSigInDataException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)

    data class InvalidSigUpDataException(
        override val message: String? = null,
        override val cause: Throwable? = null,
        val field: FieldErrorName
    ) : DomainException(message, cause) {

        enum class FieldErrorName {
            USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD
        }
    }

    data class InternalErrorException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)
}