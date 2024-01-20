package com.dreamsoftware.tvnexa.data.network.di

import com.dreamsoftware.tvnexa.BuildConfig
import com.dreamsoftware.tvnexa.data.network.service.IAuthService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private companion object {
        const val TIMEOUT_IN_MINUTES: Long = 2
    }

    /**
     * Provide Converter Factory
     */
    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory =
        MoshiConverterFactory.create(
            Moshi.Builder().build()
        )

    /**
     * Provide HTTP Client Builder
     */
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
        .readTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
        .writeTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, converter: Converter.Factory): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converter)
            .build()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): IAuthService =
        retrofit.create(IAuthService::class.java)
}