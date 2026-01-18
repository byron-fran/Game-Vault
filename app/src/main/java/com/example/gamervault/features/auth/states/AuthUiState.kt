package com.example.gamervault.features.auth.states

import com.example.gamervault.domain.models.User

data class AuthUiState(
    var isLoading : Boolean = false,
    var isAuthenticated : Boolean = false,
    var isError : String? = null,
    val user : User? = null
)