package com.example.gamervault.domain.usecases.favorites

import com.example.gamervault.domain.repositories.FavoritesRepository

class GetFavoritesUseCase (
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke() = favoritesRepository.getFavorites()

}
