package com.dreamsoftware.tvnexa

import android.app.Application
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.utils.IApplicationAware
import com.dreamsoftware.tvnexa.utils.ISessionAware
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TvNexaApp : Application(), IApplicationAware, ISessionAware {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override var session: AuthSessionBO? = null
}
