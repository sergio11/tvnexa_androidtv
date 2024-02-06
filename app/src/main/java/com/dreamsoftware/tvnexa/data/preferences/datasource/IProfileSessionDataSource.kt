package com.dreamsoftware.tvnexa.data.preferences.datasource

import com.dreamsoftware.tvnexa.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.tvnexa.data.preferences.exception.PreferencesException

/**
 * An interface representing a data source for profile session-related operations.
 */
interface IProfileSessionDataSource {

    /**
     * Saves the selected profile preference.
     * @param profile The profile selected preference DTO to be saved.
     */
    suspend fun save(profile: ProfileSelectedPreferenceDTO)

    /**
     * Retrieves the selected profile preference.
     * @return The retrieved profile selected preference DTO.
     * @throws PreferencesException.ProfileNotFoundException If the profile preference is not found.
     */
    @Throws(PreferencesException.ProfileNotFoundException::class)
    suspend fun get(): ProfileSelectedPreferenceDTO
}