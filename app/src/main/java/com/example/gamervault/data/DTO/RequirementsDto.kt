package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class RequirementsDto(
    @SerializedName("minimum")
    val minimum: String,
    @SerializedName("recommended")
    val recommended: String
)