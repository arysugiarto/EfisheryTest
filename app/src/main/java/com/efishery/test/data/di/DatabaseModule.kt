package com.efishery.test.data.di

import android.content.Context
import androidx.room.Room
import com.efishery.test.data.local.EfisheryDatabase
import com.efishery.test.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                context,
                EfisheryDatabase::class.java,
                Const.Database.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    @Singleton
    @Provides
    fun provideOrderDao(database: EfisheryDatabase) = database.orderDao()

}