package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase
import com.dreamsoftware.tvnexa.utils.AppEvent
import com.dreamsoftware.tvnexa.utils.AppEventBus
import com.dreamsoftware.tvnexa.utils.ISessionAware

class SignOffUseCase(
    private val repository: IAuthRepository,
    private val sessionAware: ISessionAware,
    private val appEventBus: AppEventBus
): BaseUseCase<Unit>() {
    override suspend fun onExecuted() {
        repository.signOff().also {
            sessionAware.session = null
            appEventBus.send(AppEvent.SignOff)
        }
    }
}