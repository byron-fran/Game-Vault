package com.example.gamervault.features.favorites.states

import com.example.gamervault.domain.models.Favorite

data class FavoritesUiState(
    var favorites: List<Favorite> = emptyList(),
    var isLoading: Boolean = false,
    var error: String? = null,
    var isFavorite : Boolean = false,
    var isFavoriteLoading : Boolean = false
)
