package com.strangenaut.timetablebstu.di

import android.app.Application
import com.strangenaut.timetablebstu.feature_timetable.util.GROUPS_TIMETABLE_URL_BASE
import com.strangenaut.timetablebstu.feature_timetable.util.TIME_OUT_MINUTES
import com.strangenaut.timetablebstu.feature_timetable.data.TimetableService
import com.strangenaut.timetablebstu.feature_timetable.data.util.TimetableDTOMapper
import com.strangenaut.timetablebstu.feature_timetable.data.util.XmlConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain
                    .request()
                    .newBuilder()
                    .header(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                "Chrome/58.0.3029.110 " +
                                "Safari/537.3"
                    )
                    .build()
                chain.proceed(request)
            }
            .readTimeout(TIME_OUT_MINUTES, TimeUnit.MINUTES)
            .connectTimeout(TIME_OUT_MINUTES, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): TimetableService {
        return Retrofit.Builder()
            .baseUrl(GROUPS_TIMETABLE_URL_BASE)
            .client(client)
            .build()
            .create(TimetableService::class.java)
    }

    @Provides
    @Singleton
    fun provideTimetableDTOMapper(): TimetableDTOMapper {
        return TimetableDTOMapper()
    }

    @Provides
    @Singleton
    fun provideXmlConverter(app: Application): XmlConverter {
        return XmlConverter(app)
    }
}