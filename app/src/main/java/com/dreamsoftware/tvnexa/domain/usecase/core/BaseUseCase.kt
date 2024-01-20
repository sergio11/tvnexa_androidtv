package com.dreamsoftware.tvnexa.domain.usecase.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<ReturnType> {

    // This fun will be used to provide the actual implementation
    // in the child class
    abstract suspend fun onExecuted(): ReturnType

    suspend operator fun invoke(
        scope: CoroutineScope
    ): ReturnType = withContext(scope.coroutineContext) { onExecuted() }

    operator fun invoke(
        scope: CoroutineScope,
        onSuccess: (result: ReturnType) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val backgroundJob = scope.async { onExecuted() }
        scope.launch {
            try {
                onSuccess(backgroundJob.await())
            } catch (ex: Exception) {
                onError(ex)
            }
        }
    }
}