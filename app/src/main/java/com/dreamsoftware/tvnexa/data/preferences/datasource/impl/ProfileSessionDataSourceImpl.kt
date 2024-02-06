package com.dreamsoftware.tvnexa.data.preferences.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dreamsoftware.tvnexa.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.tvnexa.data.preferences.exception.PreferencesException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Implementation of [IProfileSessionDataSource] for managing profile session-related data using DataStore.
 * @param dataStore The DataStore instance used for storing preferences.
 * @param moshi The Moshi instance used for JSON serialization/deserialization.
 */
internal class ProfileSessionDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val moshi: Moshi
) : IProfileSessionDataSource {

    private companion object {
        const val PROFILE_SELECTED_KEY = "PROFILE_SELECTED_KEY"
    }

    private val profileSelectedPreferenceAdapter by lazy {
        moshi.adapter(ProfileSelectedPreferenceDTO::class.java)
    }

    /**
     * Saves the selected profile preference.
     * @param profile The profile selected preference DTO to be saved.
     */
    override suspend fun save(profile: ProfileSelectedPreferenceDTO) {
        val dataStoreKey = stringPreferencesKey(PROFILE_SELECTED_KEY)
        dataStore.edit { pref ->
            pref[dataStoreKey] = profileSelectedPreferenceAdapter.toJson(profile)
        }
    }

    /**
     * Retrieves the selected profile preference.
     * @return The retrieved profile selected preference DTO.
     * @throws PreferencesException.ProfileNotFoundException If the profile preference is not found.
     */
    @Throws(PreferencesException.ProfileNotFoundException::class)
    override suspend fun get(): ProfileSelectedPreferenceDTO {
        val dataStoreKey = stringPreferencesKey(PROFILE_SELECTED_KEY)
        return dataStore.data
            .map { pref -> pref[dataStoreKey]?.let(profileSelectedPreferenceAdapter::fromJson) }
            .firstOrNull() ?: throw PreferencesException.ProfileNotFoundException("profile not found")
    }
}