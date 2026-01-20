package com.example.gamervault.domain.repositories

import com.example.gamervault.domain.models.Favorite

interface FavoritesRepository {

    suspend fun insertFavorite(favorite: Favorite)
    suspend fun getFavorites(): List<Favorite>
    suspend fun deleteFavorite(gameId: String)

}