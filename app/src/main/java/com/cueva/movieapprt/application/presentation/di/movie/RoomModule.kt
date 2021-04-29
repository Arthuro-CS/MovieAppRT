package com.cueva.movieapprt.application.presentation.di.movie

import android.app.Application
import androidx.room.Room
import com.cueva.movieapprt.application.framework.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(application: Application) {

    private var movieApplication = application
    private lateinit var appDatabase: AppDatabase


    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        appDatabase = Room.databaseBuilder(movieApplication, AppDatabase::class.java, AppDatabase.MOVIE_DB)
            .fallbackToDestructiveMigration()
            .build()
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesMovieModelDao(appDatabase: AppDatabase) = appDatabase.getMovieModelDao()

    @Singleton
    @Provides
    fun providesRepoDao(appDatabase: AppDatabase) = appDatabase.getRepoDao()
}