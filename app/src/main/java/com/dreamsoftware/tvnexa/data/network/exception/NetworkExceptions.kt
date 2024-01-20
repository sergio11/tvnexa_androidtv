package com.dreamsoftware.tvnexa.data.network.exception

/**
 * A sealed class representing network-related exceptions that may occur during network operations.
 *
 * @property message A message providing additional details about the exception.
 * @property cause The underlying cause of the exception, if available.
 */
sealed class NetworkException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    /**
     * Represents a Bad Request (HTTP 400) exception that may occur during network operations.
     */
    data class BadRequestException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents a Connection Exception that may occur during network operations.
     */
    data class ConnectException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents a generic Error Exception that may occur during network operations.
     */
    data class ErrorException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents a Forbidden (HTTP 403) exception that may occur during network operations.
     */
    data class ForbiddenException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents a No Internet Connection exception that may occur during network operations.
     */
    data class NoInternetException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents a No Result exception that may occur during network operations.
     */
    data class NoResultException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents an Unauthorized (HTTP 401) exception that may occur during network operations.
     */
    data class UnauthorizedException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)

    /**
     * Represents an Unverified Account exception that may occur during network operations.
     */
    data class UnverifiedAccountException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkException(message, cause)
}
