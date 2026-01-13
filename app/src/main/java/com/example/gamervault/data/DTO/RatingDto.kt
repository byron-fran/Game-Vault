package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("percent")
    val percent: Double,
    @SerializedName("title")
    val title: String
)