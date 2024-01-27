package com.dreamsoftware.tvnexa.data.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.datasource.impl.AuthSessionDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module providing dependencies related to DataStore preferences.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {

    /**
     * Companion object containing constants related to DataStore preferences.
     */
    private companion object {
        const val AUTH_PREFERENCE = "AUTH_PREF"
    }

    /**
     * Provides a Moshi instance for JSON serialization/deserialization.
     *
     * @return A [Moshi] instance.
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    /**
     * Provides a DataStore instance for storing preferences.
     *
     * @param context The application context.
     * @return A [DataStore] instance for preferences.
     */
    @Provides
    @Singleton
    fun providePreferenceDatastore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(AUTH_PREFERENCE)
            }
        )

    /**
     * Provides an implementation of [IAuthSessionDataSource] using DataStore.
     *
     * @param dataStore The DataStore instance for preferences.
     * @param moshi The Moshi instance for JSON serialization/deserialization.
     * @return An implementation of [IAuthSessionDataSource].
     */
    @Provides
    @Singleton
    fun provideAuthSessionDataSource(dataStore: DataStore<Preferences>, moshi: Moshi): IAuthSessionDataSource =
        AuthSessionDataSourceImpl(dataStore, moshi)
}