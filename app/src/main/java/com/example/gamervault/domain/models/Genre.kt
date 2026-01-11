package com.example.gamervault.domain.models

import com.google.gson.annotations.SerializedName

data class Genre(
    val gamesCount: Int =0 ,
    val id: Int =0,
    val imageBackground: String = "",
    val name: String = "",
    val slug: String = ""
)