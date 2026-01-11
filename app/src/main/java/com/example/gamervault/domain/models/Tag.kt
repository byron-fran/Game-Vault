package com.example.gamervault.domain.models

import com.google.gson.annotations.SerializedName

data class Tag(
    val id: Int =  0,
    val gamesCount: Int = 0,
    val imageBackground: String = "",
    val language: String = "",
    val name: String = "",
    val slug: String = ""
)