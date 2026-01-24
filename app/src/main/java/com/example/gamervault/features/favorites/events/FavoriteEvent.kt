package com.example.gamervault.features.favorites.events

import com.example.gamervault.domain.models.Game

sealed interface FavoriteEvent {

    data object GetFavorites : FavoriteEvent
    data class OnClickFavorite(val game : Game) : FavoriteEvent
    data class OnFavoriteSelected(val game : Game?) : FavoriteEvent
}