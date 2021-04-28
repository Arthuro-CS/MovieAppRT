package com.cueva.movieapprt.application.presentation.ui.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.cueva.movieapprt.application.framework.MovieDataSource
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val movieDataSource: MovieDataSource): ViewModel() {

    @ExperimentalPagingApi
    fun getMovies(): LiveData<PagingData<MovieApp>> {
        return movieDataSource.getMovies().cachedIn(viewModelScope)
    }
}