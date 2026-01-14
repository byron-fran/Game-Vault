package com.example.gamervault.features.search.states

import com.example.gamervault.domain.models.Game

data class SearchUiState(
    var query: String = "",
    val expanded: Boolean = false,
    val games: List<Game> = emptyList(),
)