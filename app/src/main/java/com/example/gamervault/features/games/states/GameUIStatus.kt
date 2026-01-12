package com.example.gamervault.features.games.states

sealed class GameUIStatus {

    data object Loading : GameUIStatus()
    data object Success : GameUIStatus()
    data class Error(val message: String) : GameUIStatus()

}