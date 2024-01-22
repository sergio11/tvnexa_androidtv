package com.dreamsoftware.tvnexa.data.preferences.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.data.preferences.exception.PreferencesException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlin.jvm.Throws

/**
 * Implementation of [IAuthSessionDataSource] for handling authentication session data using DataStore.
 *
 * @property dataStore The DataStore instance for preferences.
 * @property moshi The Moshi instance for JSON serialization/deserialization.
 */
internal class AuthSessionDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val moshi: Moshi
) : IAuthSessionDataSource {

    /**
     * Companion object containing constants related to preferences.
     */
    private companion object {
        const val AUTH_KEY = "AUTH_KEY"
    }

    private val authSessionPreferenceAdapter by lazy {
        moshi.adapter(AuthSessionPreferenceDTO::class.java)
    }

    /**
     * Saves the provided authentication session data to preferences.
     *
     * @param authSession The authentication session data to be saved.
     */
    override suspend fun save(authSession: AuthSessionPreferenceDTO) {
        val dataStoreKey = stringPreferencesKey(AUTH_KEY)
        dataStore.edit { pref ->
            pref[dataStoreKey] = authSessionPreferenceAdapter.toJson(authSession)
        }
    }

    /**
     * Retrieves the stored authentication session data from preferences.
     *
     * @return The authentication session data, or null if not present.
     */
    @Throws(PreferencesException.SessionNotFoundException::class)
    override suspend fun get(): AuthSessionPreferenceDTO {
        val dataStoreKey = stringPreferencesKey(AUTH_KEY)
        return dataStore.data
            .map { pref -> pref[dataStoreKey]?.let(authSessionPreferenceAdapter::fromJson) }
            .firstOrNull() ?: throw PreferencesException.SessionNotFoundException("Session not found")
    }
}