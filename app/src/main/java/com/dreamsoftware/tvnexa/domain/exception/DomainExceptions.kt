package com.dreamsoftware.tvnexa.domain.exception


sealed class DomainException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    data class NoRegionsFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)


    data class InternalErrorException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : DomainException(message, cause)
}