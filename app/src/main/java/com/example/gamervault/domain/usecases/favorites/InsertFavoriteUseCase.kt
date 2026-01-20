package com.example.gamervault.domain.usecases.favorites

import com.example.gamervault.domain.models.Favorite
import com.example.gamervault.domain.repositories.FavoritesRepository

class InsertFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository

) {
    suspend operator fun invoke(favorite: Favorite) = favoritesRepository.insertFavorite(favorite)

}