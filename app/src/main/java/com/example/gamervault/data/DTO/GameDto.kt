package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class GameDto(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("released")
    val released: String,

)