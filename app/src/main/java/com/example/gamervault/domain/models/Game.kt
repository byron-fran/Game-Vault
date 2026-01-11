package com.example.gamervault.domain.models

data class Game(
    val id: Int = 0,
    val name: String = "",
    val rating: Double = 0.0,
    val backgroundImage: String = "",
    val genres: List<Genre> = emptyList(),
    val released: String = "",
    val reviewsCount: Int = 0,
    val reviewsTextCount: Int = 0,
    val slug: String = "",
    val tags: List<Tag> = emptyList(),
)
