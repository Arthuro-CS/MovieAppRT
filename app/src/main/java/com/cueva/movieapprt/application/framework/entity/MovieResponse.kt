package com.cueva.movieapprt.application.framework.entity

import com.cueva.movieapprt.application.presentation.entity.MovieApp
import com.squareup.moshi.Json

data class MovieResponse (
    val results: List<MovieResult>
){
}


data class MovieResult(
    val id: Int,
    @Json(name = "title")
    val originalTitle: String,
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "poster_path")
    val urlPoster: String?,
    @Json(name="backdrop_path")
    val backPoster: String?
){
    fun mapToMovie() : MovieApp {
        return MovieApp(id,urlPoster?:"",originalTitle,voteAverage,releaseDate,overview,backPoster?:"")
    }

}