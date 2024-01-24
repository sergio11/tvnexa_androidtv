package com.dreamsoftware.tvnexa.data.repository.core

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal open class SupportRepositoryImpl {

    protected suspend fun <T> safeExecute(block: suspend () -> T) = withContext(Dispatchers.IO) {
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