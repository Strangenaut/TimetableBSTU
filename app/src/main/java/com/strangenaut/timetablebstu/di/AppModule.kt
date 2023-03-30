package com.strangenaut.timetablebstu.di

import android.content.Context
import com.strangenaut.timetablebstu.TimetableBSTUApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TimetableBSTUApp {
        return app as TimetableBSTUApp
    }
}