package com.efishery.test.data.di

import android.app.Application
import android.content.Context
import com.efishery.test.data.local.preferences.AccessManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    fun provideApplication(application: Application): Context = application

    @Provides
    fun provideAccessManager(context: Context) = AccessManager(context)

    // Todo : Add Repositories & Injectable Application Module here

}