package com.dreamsoftware.tvnexa.data.preferences.datasource

import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO

/**
 * Interface defining methods for handling authentication session data.
 */
interface IAuthSessionDataSource {

    /**
     * Saves the provided authentication session data.
     *
     * @param authSession The authentication session data to be saved.
     */
    suspend fun save(authSession: AuthSessionPreferenceDTO)

    /**
     * Retrieves the stored authentication session data.
     *
     * @return The authentication session data, or null if not present.
     */
    suspend fun get(): AuthSessionPreferenceDTO
}