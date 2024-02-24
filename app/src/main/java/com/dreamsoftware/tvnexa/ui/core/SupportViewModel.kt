package com.dreamsoftware.tvnexa.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Abstract base class for ViewModels that support UI state management and side effects.
 *
 * @param STATE The type representing the UI state.
 * @param EFFECT The type representing side effects.
 */
abstract class SupportViewModel<STATE : UiState<STATE>, EFFECT : SideEffect> : ViewModel() {

    // MutableStateFlow for managing UI state
    private val _uiState: MutableStateFlow<STATE> by lazy {
        MutableStateFlow(onGetDefaultState())
    }
    // Immutable StateFlow for observing UI state changes
    val uiState: StateFlow<STATE> = _uiState

    // MutableSharedFlow for emitting side effects
    private val _sideEffect: MutableSharedFlow<EFFECT> by lazy {
        MutableSharedFlow()
    }
    // Immutable SharedFlow for observing side effects
    val sideEffect: SharedFlow<EFFECT> = _sideEffect.asSharedFlow()

    /**
     * Provides the default UI state when the ViewModel is initialized.
     *
     * @return The default UI state.
     */
    abstract fun onGetDefaultState(): STATE

    fun onErrorAccepted() {
        updateState { it.copyState(error = null) }
    }

    /**
     * Updates the UI state using the provided reducer function.
     *
     * @param reducer A function that takes the current state and returns the new state.
     */
    protected fun updateState(reducer: (currentState: STATE) -> STATE) {
        _uiState.value = reducer(_uiState.value)
    }

    /**
     * Launches a side effect by emitting it to the SharedFlow.
     *
     * @param effect The side effect to be emitted.
     */
    protected fun launchSideEffect(effect: EFFECT) {
        viewModelScope.launch {
            _sideEffect.emit(effect)
        }
    }

    protected suspend fun <RESULT, UC: BaseUseCase<RESULT>> executeUseCase(
        useCase: UC,
        onGetDefaultValue: () -> RESULT,
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
    ): RESULT {
        onLoading()
        return try {
            useCase.invoke(scope = viewModelScope)
        } catch (ex: Exception) {
            onErrorOccurred(ex, onMapExceptionToState)
            onGetDefaultValue()
        } finally {
            onIdle()
        }
    }

    protected fun <RESULT, UC: BaseUseCase<RESULT>> executeUseCase(
        useCase: UC,
        onSuccess: (RESULT) -> Unit = {},
        onFailed: () -> Unit = {},
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null
    ) {
        onLoading()
        useCase.invoke(
            scope = viewModelScope,
            onSuccess = {
                onIdle()
                onSuccess(it)
            },
            onError = {
                onErrorOccurred(it, onMapExceptionToState)
                onFailed()
            }
        )
    }

    protected suspend fun <PARAMS, RESULT, UC: BaseUseCaseWithParams<PARAMS, RESULT>> executeUseCaseWithParams(
        useCase: UC,
        params: PARAMS,
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
        onGetDefaultValue: () -> RESULT
    ): RESULT {
        onLoading()
        return try {
            useCase.invoke(
                scope = viewModelScope,
                params = params
            )
        } catch (ex: Exception) {
            onErrorOccurred(ex, onMapExceptionToState)
            onGetDefaultValue()
        } finally {
            onIdle()
        }
    }

    protected fun <PARAMS, RESULT, UC: BaseUseCaseWithParams<PARAMS, RESULT>> executeUseCaseWithParams(
        useCase: UC,
        params: PARAMS,
        onSuccess: (RESULT) -> Unit = {},
        onFailed: () -> Unit = {},
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null
    ) {
        onLoading()
        useCase.invoke(
            scope = viewModelScope,
            params = params,
            onSuccess = {
                onIdle()
                onSuccess(it)
            },
            onError = {
                onErrorOccurred(it, onMapExceptionToState)
                onFailed()
            }
        )
    }

    private fun onLoading() {
        updateState {
            it.copyState(isLoading = true, error = null)
        }
    }

    private fun onIdle() {
        updateState {
            it.copyState(isLoading = false)
        }
    }

    private fun onErrorOccurred(ex: Exception, onMapExceptionToState: ((Exception, STATE) -> STATE)?) {
        ex.printStackTrace()
        updateState {
            onMapExceptionToState?.invoke(ex, it.copyState(isLoading = false)) ?: run {
                it.copyState(
                    isLoading = false,
                    error = ex.message
                )
            }
        }
    }
}

/**
 * Interface representing the UI state.
 */
abstract class UiState<out T: UiState<T>>(
    open val isLoading: Boolean,
    open val error: String?
) {
    abstract fun copyState(
        isLoading: Boolean = this.isLoading,
        error: String? = this.error
    ): T
}

/**
 * Interface representing side effects.
 */
interface SideEffect