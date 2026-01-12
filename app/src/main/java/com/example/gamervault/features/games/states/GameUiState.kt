package com.example.gamervault.features.games.states

import com.example.gamervault.domain.models.Game

data class GameUiState(
    val games : List<Game> = emptyList(),
    val gameDetail : Game? = null,
    val isLoading : Boolean = false,
    val error : String = "",

)
