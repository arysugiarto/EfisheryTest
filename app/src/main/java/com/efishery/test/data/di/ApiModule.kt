package com.efishery.test.data.di

import com.efishery.test.data.remote.api.ParentingHubApiCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    @Provides
    fun provideDefaultApiCallback(retrofit: Retrofit): ParentingHubApiCallback =
            retrofit.create(ParentingHubApiCallback::class.java)
}