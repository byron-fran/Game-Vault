package com.example.gamervault.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamervault.core.mappers.toDomain
import com.example.gamervault.data.source.remote.api.GamesVaultApi
import com.example.gamervault.domain.models.Game

class GamesVaultPagingSource(
    private val gamesVaultApi: GamesVaultApi
): PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?:
                state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {

        val pageIndex = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val response = gamesVaultApi.getGames(pageIndex, pageSize)
            LoadResult.Page(
                data = response.body()?.games?.map { it.toDomain() } ?: emptyList(),
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (response.body()?.games?.isEmpty() == true) null else pageIndex + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }


}