package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class PlatformDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
