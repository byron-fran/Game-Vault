package com.example.gamervault.data.DTO

import com.google.gson.annotations.SerializedName

data class GameResponseDto(

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("filters")
    val filters: FiltersDto,

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("nofollow")
    val noFollow: Boolean,

    @field:SerializedName("nofollow_collections")
    val noFollowCollections: List<String>,

    @field:SerializedName("noindex")
    val noIndex: Boolean,

    @field:SerializedName("previous")
    val previous: Any,

    @field:SerializedName("results")
    val games: List<GameDto>,

    @field:SerializedName("seo_description")
    val seoDescription: String,

    @field:SerializedName("seo_h1")
    val seoH1: String,

    @field:SerializedName("seo_keywords")
    val seoKeywords: String,

    @field:SerializedName("seo_title")
    val seoTitle: String

)


