package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class PlatformXDto(
    @SerializedName("platform")
    val platform: PlatformDto,
    @SerializedName("released_at")// released_at
    val releasedAt: String,
    @SerializedName("requirements")
    val requirements: RequirementsDto
)
