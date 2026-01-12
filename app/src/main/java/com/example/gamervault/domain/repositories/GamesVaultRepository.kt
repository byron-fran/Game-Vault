package com.example.gamervault.domain.repositories

import androidx.paging.PagingData
import com.example.gamervault.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GamesVaultRepository {
    fun getGames(pageSize: Int, enablePlaceHolders: Boolean, prefetchDistance: Int, initialLoadSize: Int, maxCacheSize: Int) : Flow<PagingData<Game>>
    suspend fun getGameById(id: Int) : Game?

}