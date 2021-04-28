package com.cueva.movieapprt.application.framework.network

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import java.io.IOException
import javax.inject.Inject

class MoviesPagingSource  @Inject constructor(val movieService: MovieService):
    PagingSource<Int, MovieApp>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieApp> {
        val page = params.key ?: 1
        return try {

            val response = movieService.getMovies(page,"f46b58478f489737ad5a4651a4b25079").results.map {
                it.mapToMovie()
            }
            LoadResult.Page(
                response, prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }
}