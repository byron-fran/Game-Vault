package com.example.gamervault.core.mappers

import com.example.gamervault.data.DTO.GameDto
import com.example.gamervault.data.DTO.GenreDto
import com.example.gamervault.data.DTO.TagDto
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.models.Genre
import com.example.gamervault.domain.models.Tag


fun GameDto.toDomain() = Game(

    id = this.id,
    name = this.name,
    rating = this.rating,
    backgroundImage = this.backgroundImage,
    released = this.released,
    reviewsCount = this.reviewsCount,
    reviewsTextCount = this.reviewsTextCount,
    slug = this.slug
)

fun GenreDto.toDomain() = Genre(
    id = this.id,
    name = this.name,
    slug = this.slug,
    gamesCount = this.gamesCount,
    imageBackground = this.imageBackground
)
fun TagDto.toDomain() = Tag(
    id = this.id,
    gamesCount = this.gamesCount,
    imageBackground = this.imageBackground,
    language = this.language,
    name = this.name,
    slug = this.slug,

)