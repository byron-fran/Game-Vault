package com.example.gamervault.domain.usecases.favorites

import com.example.gamervault.domain.repositories.FavoritesRepository

class DeleteFavoriteByIdUseCase constructor(
    private val favoritesRepository: FavoritesRepository

) {
    suspend operator fun invoke(id : String) = favoritesRepository.deleteFavorite(id)

}