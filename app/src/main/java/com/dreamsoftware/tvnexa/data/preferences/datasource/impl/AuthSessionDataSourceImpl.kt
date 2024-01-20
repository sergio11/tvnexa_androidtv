package com.dreamsoftware.tvnexa.data.preferences.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull

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

    /**
     * Saves the provided authentication session data to preferences.
     *
     * @param authSession The authentication session data to be saved.
     */
    override suspend fun save(authSession: AuthSessionPreferenceDTO) {
        val dataStoreKey = stringPreferencesKey(AUTH_KEY)
        dataStore.edit { pref ->
            pref[dataStoreKey] = moshi.adapter(AuthSessionPreferenceDTO::class.java)
                .toJson(authSession)
        }
    }

    /**
     * Retrieves the stored authentication session data from preferences.
     *
     * @return The authentication session data, or null if not present.
     */
    override suspend fun get(): AuthSessionPreferenceDTO {
        val dataStoreKey = stringPreferencesKey(AUTH_KEY)
        return dataStore.data.mapNotNull { pref ->
            pref[dataStoreKey]?.let {
                moshi.adapter(AuthSessionPreferenceDTO::class.java).fromJson(it)
            }
        }.first()
    }
}