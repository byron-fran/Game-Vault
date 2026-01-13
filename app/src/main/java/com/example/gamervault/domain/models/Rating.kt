package com.example.gamervault.domain.models

data class Rating(
    val id : Int,
    val count: Int =0,
    val percent: Double = 0.0,
    val title: String = ""
)
