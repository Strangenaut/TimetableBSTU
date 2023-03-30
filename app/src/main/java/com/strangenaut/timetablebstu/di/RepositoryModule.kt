package com.strangenaut.timetablebstu.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.strangenaut.timetablebstu.feature_timetable.data.TimetableService
import com.strangenaut.timetablebstu.feature_timetable.data.repository.TimetableRepositoryImpl
import com.strangenaut.timetablebstu.feature_timetable.data.util.TimetableDTOMapper
import com.strangenaut.timetablebstu.feature_timetable.data.util.XmlConverter
import com.strangenaut.timetablebstu.feature_timetable.domain.repository.TimetableRepository
import com.strangenaut.timetablebstu.feature_timetable.domain.use_case.*
import com.strangenaut.timetablebstu.feature_timetable.util.GROUPS_TIMETABLE_FILENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTimetableRepository(
        timetableService: TimetableService,
        timetableDTOMapper: TimetableDTOMapper,
        internalJsonFile: File,
        xmlConverter: XmlConverter,
        sharedPreferences: SharedPreferences,
    ): TimetableRepository {
        return TimetableRepositoryImpl(
            timetableService,
            timetableDTOMapper,
            internalJsonFile,
            xmlConverter,
            sharedPreferences
        )
    }

    @Provides
    @Singleton
    fun provideTimetableUseCases(repository: TimetableRepository): TimetableUseCases {
        return TimetableUseCases(
            loadTimetableFromInternalStorage = LoadTimetableFromInternalStorage(repository),
            loadTimetableFromNetwork = LoadTimetableFromNetwork(repository),
            getCurrentWeekCode = GetCurrentWeekCode(repository),
            getGroupsNumbersList = GetGroupsNumbersList(repository),
            saveTimetableToJson = SaveTimetableToJson(repository),
            loadUserSettings = LoadUserSettings(repository),
            saveUserSettings = SaveUserSettings(repository),
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideInternalJsonFile(app: Application): File {
        return File(app.applicationContext.filesDir, GROUPS_TIMETABLE_FILENAME)
    }
}