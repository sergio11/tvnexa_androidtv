package com.dreamsoftware.tvnexa.ui.di

import android.content.Context
import com.dreamsoftware.tvnexa.ui.features.signin.error.SignInEmailErrorMapper
import com.dreamsoftware.tvnexa.ui.features.signin.error.SignInPasswordErrorMapper
import com.dreamsoftware.tvnexa.ui.features.signin.error.SignInScreenErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UiModule {

    @Provides
    @ViewModelScoped
    fun provideSignInScreenErrorMapper(@ApplicationContext context: Context) = SignInScreenErrorMapper(context)

    @Provides
    @ViewModelScoped
    fun provideSignInFormErrorMapper(@ApplicationContext context: Context) = SignInEmailErrorMapper(context)

    @Provides
    @ViewModelScoped
    fun provideSignInPasswordErrorMapper(@ApplicationContext context: Context) = SignInPasswordErrorMapper(context)

}