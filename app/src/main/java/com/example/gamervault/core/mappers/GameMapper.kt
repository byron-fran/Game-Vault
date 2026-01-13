package com.example.gamervault.core.mappers

import com.example.gamervault.data.DTO.GameDetailDto
import com.example.gamervault.data.DTO.GameDto
import com.example.gamervault.data.DTO.GenreDto
import com.example.gamervault.data.DTO.PlatformDto
import com.example.gamervault.data.DTO.PlatformXDto
import com.example.gamervault.data.DTO.RequirementsDto
import com.example.gamervault.data.DTO.TagDto
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.models.Genre
import com.example.gamervault.domain.models.Platform
import com.example.gamervault.domain.models.PlatformX
import com.example.gamervault.domain.models.Requirements
import com.example.gamervault.domain.models.Tag


fun GameDto.toDomain() = Game(

    id = this.id,
    name = this.name,
    backgroundImage = this.backgroundImage,
    rating = this.rating,
    released = this.released,
)
fun GameDetailDto.toDomain() = Game(
    id = this.id,
    description = this.description,
    name = this.name,
    backgroundImage = this.backgroundImage,
    rating = this.rating,
    released = this.released,
    tags = this.tags.map { it.toDomain() },
    genres = this.genres.map { it.toDomain() },
    platforms = this.platforms.map { it.toDomain()},

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

fun PlatformXDto.toDomain() = PlatformX(
    platform = this.platform.toDomain(),
    releasedAt = this.releasedAt,
    requirements = this.requirements.toDomain()
)

fun PlatformDto.toDomain() = Platform(
    id = this.id,
    name = this.name,
    slug = this.slug
)

fun RequirementsDto.toDomain() = Requirements(
    minimum = this.minimum,
    recommended = this.recommended
)