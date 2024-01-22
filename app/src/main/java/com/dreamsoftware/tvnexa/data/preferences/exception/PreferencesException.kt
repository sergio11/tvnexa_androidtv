package com.dreamsoftware.tvnexa.data.preferences.exception

/**
 * A sealed class representing exceptions related to preferences operations.
 * @param message A human-readable error message describing the exception.
 * @param cause The cause of the exception, if available.
 */
sealed class PreferencesException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    /**
     * Exception indicating that a session was not found in preferences.
     * @param message A human-readable error message describing the exception.
     * @param cause The cause of the exception, if available.
     */
    data class SessionNotFoundException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ): PreferencesException(message, cause)
}