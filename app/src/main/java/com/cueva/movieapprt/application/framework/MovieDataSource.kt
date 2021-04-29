package com.cueva.movieapprt.application.framework

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.cueva.movieapprt.application.framework.local.AppDatabase
import com.cueva.movieapprt.application.framework.network.MovieService
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import javax.inject.Inject

class MovieDataSource @Inject constructor(val movieService: MovieService, val appDatabase: AppDatabase){

    companion object  {
        const val DEFAULT_PAGE_SIZE = 20
    }

    @ExperimentalPagingApi
    fun getMovies(): LiveData<PagingData<MovieApp>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { appDatabase.getMovieModelDao().getAllMovieModel() }
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MovieMediator(movieService, appDatabase)
        ).liveData
    }


    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}