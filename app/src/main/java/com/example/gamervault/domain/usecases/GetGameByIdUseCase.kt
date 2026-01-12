package com.example.gamervault.domain.usecases

import com.example.gamervault.domain.repositories.GamesVaultRepository

class GetGameByIdUseCase (
    private val repository: GamesVaultRepository
) {

    suspend operator fun invoke(id: Int) = repository.getGameById(id)
}