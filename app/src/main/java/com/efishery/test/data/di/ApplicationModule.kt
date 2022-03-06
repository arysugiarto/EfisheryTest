package com.efishery.test.data.di

import android.app.Application
import android.content.Context
import com.efishery.test.data.local.dao.OrderDao
import com.efishery.test.data.local.preferences.AccessManager
import com.efishery.test.data.remote.api.ApiCallback
import com.efishery.test.data.repository.HomeRepository
import com.efishery.test.data.repository.OrderRepository
import com.efishery.test.data.source.OrderLocalDataSource
import com.efishery.test.data.source.data.HomeRemoteDataSource
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

    @Provides
    fun provideHomeRepository(
        apiCallback: ApiCallback
    ) = HomeRepository(
        HomeRemoteDataSource(apiCallback)
    )

    @Provides
    fun provideOrderRepository(
       orderDao: OrderDao
    ) = OrderRepository(
        OrderLocalDataSource(orderDao)
    )

}