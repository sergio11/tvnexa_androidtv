package com.dreamsoftware.tvnexa.utils.di

import android.content.Context
import com.dreamsoftware.tvnexa.utils.AppEventBus
import com.dreamsoftware.tvnexa.utils.IApplicationAware
import com.dreamsoftware.tvnexa.utils.ISessionAware
import com.dreamsoftware.tvnexa.utils.network.NetworkConnectivityCallback
import com.dreamsoftware.tvnexa.utils.network.NetworkConnectivityMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    /**
     * Provide Application Aware
     */
    @Singleton
    @Provides
    fun provideApplicationAware(@ApplicationContext context: Context): IApplicationAware =
        context as IApplicationAware


    @Singleton
    @Provides
    fun provideSessionAware(@ApplicationContext context: Context): ISessionAware =
        context as ISessionAware

    @Singleton
    @Provides
    fun provideAppEventBus() = AppEventBus()

    @Singleton
    @Provides
    fun provideNetworkCallback(appEventBus: AppEventBus) = NetworkConnectivityCallback(appEventBus)

    @Singleton
    @Provides
    fun provideNetworkConnectivityMonitor(
        @ApplicationContext context: Context,
        networkConnectivityCallback: NetworkConnectivityCallback
    ) = NetworkConnectivityMonitor(context, networkConnectivityCallback)
}