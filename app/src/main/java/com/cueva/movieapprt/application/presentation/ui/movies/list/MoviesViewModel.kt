package com.cueva.movieapprt.application.presentation.ui.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.cueva.movieapprt.application.framework.network.MoviesPagingSource
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val moviePagingSource: MoviesPagingSource): ViewModel() {

    companion object  {
        const val DEFAULT_PAGE_SIZE = 20
    }

    fun getMoviesPagedLiveData() : LiveData<PagingData<MovieApp>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = { moviePagingSource }
        ).liveData.cachedIn(viewModelScope)
    }
}