package com.cueva.movieapprt.application.presentation.ui.movies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cueva.movieapprt.application.presentation.entity.MovieApp

class MovieDetailViewModelFactory (
    private val movie: MovieApp
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}