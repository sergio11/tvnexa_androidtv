package com.dreamsoftware.tvnexa.domain.usecase.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCaseWithParams<ParamsType, ReturnType> {

    // This fun will be used to provide the actual implementation
    // in the child class
    abstract suspend fun onExecuted(params: ParamsType): ReturnType

    open suspend fun onReverted(params: ParamsType) {}

    suspend operator fun invoke(
        scope: CoroutineScope,
        params: ParamsType
    ): ReturnType = withContext(scope.coroutineContext) { onExecuted(params) }

    operator fun invoke(
        scope: CoroutineScope,
        params: ParamsType,
        onBeforeStart: () -> Unit = {},
        onSuccess: (result: ReturnType) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val backgroundJob = scope.async {
            onBeforeStart()
            onExecuted(params)
        }
        scope.launch {
            try {
                onSuccess(backgroundJob.await())
            } catch (ex: Exception) {
                onReverted(params)
                onError(ex)
            }
        }
    }
}