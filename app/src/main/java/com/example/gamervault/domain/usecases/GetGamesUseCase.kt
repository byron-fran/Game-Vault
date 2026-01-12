package com.example.gamervault.domain.usecases

import androidx.paging.PagingData
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.repositories.GamesVaultRepository
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase (
    private val repository: GamesVaultRepository
) {

    operator fun invoke() : Flow<PagingData<Game>> {
        return repository.getGames(
            pageSize = 10,
            enablePlaceHolders = false,
            prefetchDistance = 10,
            initialLoadSize = 20,
            maxCacheSize = 2000
        )
    }

}