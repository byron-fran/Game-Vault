package com.example.gamervault.domain.usecases

import com.example.gamervault.domain.repositories.GamesVaultRepository

class SearchGamesUseCase(
    private val repository: GamesVaultRepository
) {

    suspend operator fun invoke(query: String) = repository.searchGames(query)
}