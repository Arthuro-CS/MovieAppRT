package com.cueva.movieapprt.application.presentation.di.movie

import android.app.Application
import com.cueva.movieapprt.application.presentation.ui.movies.list.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,RoomModule::class])
interface MovieComponent {
    fun inject(moviesFragment: MoviesFragment)
    fun inject(myApp: Application?)
}