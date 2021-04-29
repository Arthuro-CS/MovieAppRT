package com.cueva.movieapprt.application.framework

import androidx.paging.*
import androidx.room.withTransaction
import com.cueva.movieapprt.application.framework.entity.RemoteKeys
import com.cueva.movieapprt.application.framework.local.AppDatabase
import com.cueva.movieapprt.application.framework.network.MovieService
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import java.io.InvalidObjectException
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class MovieMediator @Inject constructor(val movieService: MovieService, val appDatabase: AppDatabase) :
    RemoteMediator<Int, MovieApp>() {

    companion object  {
        const val DEFAULT_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MovieApp>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }
        var response = listOf<MovieApp>()
        val hasData = appDatabase.getMovieModelDao().countMovies() > 0
        var serviceError = false
        try {
            response = movieService.getMovies(page,"f46b58478f489737ad5a4651a4b25079").results.map {
                it.mapToMovie()
            }
        } catch (exception : Exception){
            serviceError = true
            if(!hasData)
                return MediatorResult.Error(exception)
        }

        try {
            val isEndOfList = response.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH && (!serviceError)) {
                    appDatabase.getRepoDao().clearRemoteKeys()
                    appDatabase.getMovieModelDao().clearAllMovies()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1

                response.forEach {
                    val idInserted : Long = appDatabase.getMovieModelDao().insert(it)
                    appDatabase.getRepoDao().insert(RemoteKeys(repoId = idInserted.toString(), prevKey = prevKey, nextKey = nextKey))
                }
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception : Exception){
            return MediatorResult.Error(exception)
        }
    }


    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, MovieApp>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                MediatorResult.Success(true)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieApp>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> appDatabase.getRepoDao().remoteKeysMovieId(movie.dataBaseId.toString()) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieApp>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> appDatabase.getRepoDao().remoteKeysMovieId(movie.dataBaseId.toString()) }
    }


    private suspend fun getClosestRemoteKey(state: PagingState<Int, MovieApp>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.dataBaseId?.let { repoId ->
                appDatabase.getRepoDao().remoteKeysMovieId(repoId.toString())
            }
        }
    }
}