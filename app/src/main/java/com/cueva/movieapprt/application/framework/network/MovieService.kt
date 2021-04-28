package com.cueva.movieapprt.application.framework.network

import com.cueva.movieapprt.application.framework.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/upcoming")
    suspend fun getMovies(@Query("page") page: Int,@Query("api_key") apiKey: String) : MovieResponse
}