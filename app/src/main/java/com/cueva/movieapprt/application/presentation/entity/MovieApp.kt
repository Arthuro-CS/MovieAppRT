package com.cueva.movieapprt.application.presentation.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class MovieApp (
    val id : Int,
    val urlPoster: String,
    val name: String,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String,
    val backPoster: String,
    @PrimaryKey(autoGenerate = true)
    val dataBaseId : Long = 0) : Parcelable {
}