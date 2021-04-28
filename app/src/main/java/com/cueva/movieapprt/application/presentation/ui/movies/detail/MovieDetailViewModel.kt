package com.cueva.movieapprt.application.presentation.ui.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import com.cueva.movieapprt.application.presentation.util.Utils

class MovieDetailViewModel (movie: MovieApp) : ViewModel() {
    private val _selectedMovie = MutableLiveData<MovieApp>()
    val selectedMovie: LiveData<MovieApp> get() = _selectedMovie

    init {
        _selectedMovie.value = movie
    }

    val date = Transformations.map(selectedMovie){
        Utils.getDateFormat(selectedMovie.value?.releaseDate?:"")
    }
}