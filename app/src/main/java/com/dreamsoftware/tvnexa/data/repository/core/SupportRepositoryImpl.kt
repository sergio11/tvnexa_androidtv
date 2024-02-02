package com.dreamsoftware.tvnexa.data.repository.core

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Base class for repository implementations providing common functionality and error handling.
 *
 * This class includes a function for executing suspend functions safely, handling exceptions and wrapping them in
 * [DomainException.InternalErrorException] for unexpected errors.
 */
internal open class SupportRepositoryImpl {

    /**
     * Executes a suspend function safely, handling exceptions and wrapping them in [DomainException.InternalErrorException].
     *
     * @param block The suspend function to be executed.
     * @return The result of the executed suspend function.
     * @throws DomainException.InternalErrorException if an unexpected error occurs during execution.
     */
    protected suspend fun <T> safeExecute(block: suspend () -> T): T = withContext(Dispatchers.IO) {
        try {
            block()
        } catch (ex: Exception) {
            throw if (ex is DomainException) {
                ex
            } else {
                DomainException.InternalErrorException("An error occurred", ex)
            }
        }
    }
}