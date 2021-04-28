package com.cueva.movieapprt.application.framework.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cueva.movieapprt.application.presentation.entity.MovieApp

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieApp) : Long

    @Query("SELECT * FROM movies")
    fun getAllMovieModel(): PagingSource<Int, MovieApp>

    @Query("SELECT count(*) FROM movies")
    suspend fun countMovies(): Int

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()
}