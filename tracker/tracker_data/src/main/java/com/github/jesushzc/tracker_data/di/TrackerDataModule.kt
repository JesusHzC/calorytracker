package com.github.jesushzc.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.github.jesushzc.tracker_data.local.TrackerDatabase
import com.github.jesushzc.tracker_data.remote.OpenFoodApi
import com.github.jesushzc.tracker_data.repository.TrackerRepositoryImpl
import com.github.jesushzc.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(OpenFoodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        dao: TrackerDatabase,
        api: OpenFoodApi
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao.trackerDao(),
            api
        )
    }

}