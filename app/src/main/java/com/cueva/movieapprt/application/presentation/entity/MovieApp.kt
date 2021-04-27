package com.cueva.movieapprt.application.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieApp (
    val id : Int,
    val urlPoster: String,
    val name: String,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String,
    val backPoster: String) : Parcelable {
}