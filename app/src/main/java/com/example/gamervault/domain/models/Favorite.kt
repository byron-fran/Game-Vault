package com.example.gamervault.domain.models


data class Favorite(
    val gameId: String = "",
    val name: String = "",
    val coverUrl: String? = "",
    val rating: Double = 0.0,
    val released: String? = "",
)
