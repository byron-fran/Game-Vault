package com.example.gamervault.data.repositories

import android.net.ConnectivityManager.NetworkCallback
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gamervault.core.mappers.toDomain
import com.example.gamervault.data.remote.GamesVaultApi
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.repositories.GamesVaultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamesVaultRepositoryImpl @Inject constructor(
    private val gamesVaultApi: GamesVaultApi
) : GamesVaultRepository, NetworkCallback() {

    override fun getGames(
        pageSize: Int,
        enablePlaceHolders: Boolean,
        prefetchDistance: Int,
        initialLoadSize: Int,
        maxCacheSize: Int
    ): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = enablePlaceHolders,
                prefetchDistance = prefetchDistance,
                initialLoadSize = initialLoadSize,
                maxSize = maxCacheSize

            ),
            pagingSourceFactory = {
                GamesVaultPagingSource(gamesVaultApi)
            }
        ).flow
    }

    override suspend fun searchGames(query: String): List<Game> {
        return gamesVaultApi.searchGames(query).body()?.games?.map { it.toDomain() } ?: emptyList()
    }
    override suspend fun getGameById(id: Int): Game? {
        return gamesVaultApi.getGameById(id)?.toDomain()
    }
}