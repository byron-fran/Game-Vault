package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class GameDetailDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("rating")
    val rating: Double,

    @SerializedName("released")
    val released: String,

    @SerializedName("background_image")
    val backgroundImage: String,

    @SerializedName("genres")
    val genres: List<GenreDto>,

    @SerializedName("tags")
    val tags: List<TagDto>,

    @SerializedName("platforms")
    val platforms: List<PlatformXDto>,

    @SerializedName("ratings")
    val ratings: List<RatingDto>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerializedName("saturated_color")

    val slug: String,

)