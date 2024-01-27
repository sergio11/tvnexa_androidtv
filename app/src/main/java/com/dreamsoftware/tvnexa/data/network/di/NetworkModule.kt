package com.dreamsoftware.tvnexa.data.network.di

import com.dreamsoftware.tvnexa.BuildConfig
import com.dreamsoftware.tvnexa.data.network.interceptors.AuthInterceptor
import com.dreamsoftware.tvnexa.data.network.service.IAuthService
import com.dreamsoftware.tvnexa.data.network.service.ICategoryService
import com.dreamsoftware.tvnexa.data.network.service.IChannelsService
import com.dreamsoftware.tvnexa.data.network.service.ICountryService
import com.dreamsoftware.tvnexa.data.network.service.IEpgService
import com.dreamsoftware.tvnexa.data.network.service.IRegionService
import com.dreamsoftware.tvnexa.data.network.service.ISubdivisionService
import com.dreamsoftware.tvnexa.utils.ISessionAware
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )

    /**
     * Provide Request Interceptors
     */
    @Provides
    @ElementsIntoSet
    @Singleton
    @Named("requestInterceptors")
    fun provideRequestInterceptors(
        sessionAware: ISessionAware
    ): Set<Interceptor> =
        setOf(AuthInterceptor(sessionAware))


    /**
     * Provide Network Interceptors
     */
    @Provides
    @ElementsIntoSet
    @Singleton
    @Named("networkInterceptors")
    fun provideNetworkInterceptors(): Set<Interceptor> =
        setOf(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.HEADERS)
        })

    /**
     * Provide HTTP Client Builder
     */
    @Provides
    @Singleton
    fun provideHttpClient(
        @Named("requestInterceptors") requestInterceptors: Set<@JvmSuppressWildcards Interceptor>,
        @Named("networkInterceptors") networkInterceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
        requestInterceptors.forEach {
            okHttpBuilder.addInterceptor(it)
        }
        networkInterceptors.forEach {
            okHttpBuilder.addNetworkInterceptor(it)
        }
        return okHttpBuilder.build()
    }

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

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit): ICategoryService =
        retrofit.create(ICategoryService::class.java)

    @Provides
    @Singleton
    fun provideChannelService(retrofit: Retrofit): IChannelsService =
        retrofit.create(IChannelsService::class.java)

    @Provides
    @Singleton
    fun provideCountryService(retrofit: Retrofit): ICountryService =
        retrofit.create(ICountryService::class.java)

    @Provides
    @Singleton
    fun provideEpgService(retrofit: Retrofit): IEpgService =
        retrofit.create(IEpgService::class.java)

    @Provides
    @Singleton
    fun provideRegionService(retrofit: Retrofit): IRegionService =
        retrofit.create(IRegionService::class.java)

    @Provides
    @Singleton
    fun provideSubdivisionService(retrofit: Retrofit): ISubdivisionService =
        retrofit.create(ISubdivisionService::class.java)
}