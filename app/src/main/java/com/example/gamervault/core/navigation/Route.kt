package com.example.gamervault.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {

    @Serializable
    data object GameScreen : Route()

    @Serializable
    data class GameDetailScreen(val id: Int) : Route()

    @Serializable
    data object FavoritesScreen : Route()

    @Serializable
    data object SearchScreen : Route()

    @Serializable
    data object AccountScreen : Route()
    @Serializable
    data object SignInScreen : Route()

    @Serializable
    data object SignUpScreen : Route()
}
