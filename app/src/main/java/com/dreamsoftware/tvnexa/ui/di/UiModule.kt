package com.dreamsoftware.tvnexa.ui.di

import android.content.Context
import com.dreamsoftware.tvnexa.ui.core.FormErrorMapperImpl
import com.dreamsoftware.tvnexa.ui.features.signin.error.SignInScreenSimpleErrorMapper
import com.dreamsoftware.tvnexa.ui.core.IFormErrorMapper
import com.dreamsoftware.tvnexa.ui.features.signup.error.SignUpScreenSimpleErrorMapper
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
    fun provideSignInScreenErrorMapper(@ApplicationContext context: Context) = SignInScreenSimpleErrorMapper(context)

    @Provides
    @ViewModelScoped
    fun provideSignUpScreenErrorMapper(@ApplicationContext context: Context) = SignUpScreenSimpleErrorMapper(context)

    @Provides
    @ViewModelScoped
    fun provideFormErrorMapper(@ApplicationContext context: Context): IFormErrorMapper = FormErrorMapperImpl(context)
}