package com.example.gamervault.features.games.events

sealed class GameUiEvents{
    data class GetGameById(val id: Int) : GameUiEvents()
    data object ClearGameDetail : GameUiEvents()

}