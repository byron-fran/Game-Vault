package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class GameDto(

    @SerializedName("added")
    val added: Int,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatusDto,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("clip")
    val clip: Any,
    @SerializedName("dominant_color")
    val dominantColor: String,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRatingDto,
    @SerializedName("genres")
    val genres: List<GenreDto>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("metacritic")
    val metaCritic: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformDto>,
    @SerializedName("platforms")
    val platforms: List<PlatformXDto>,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings")
    val ratings: List<RatingDto>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("released")
    val released: String,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotDto>,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("stores")
    val stores: List<StoreDto>,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("tags")
    val tags: List<TagDto>,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_game")
    val userGame: Any
)