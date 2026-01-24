package com.example.gamervault.features.auth.events

sealed interface AuthEvent {

    data class SignIn(val email: String, val password: String) : AuthEvent
    data class SignUp(val email: String, val password: String, val username: String) : AuthEvent
    data object SignOut : AuthEvent
    data object ClearErrors : AuthEvent
}