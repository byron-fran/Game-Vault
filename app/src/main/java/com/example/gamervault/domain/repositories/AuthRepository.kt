package com.example.gamervault.domain.repositories

import com.example.gamervault.domain.models.User

interface AuthRepository {

    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, username: String?)
    suspend fun signOut()
    suspend fun getCurrentUser() : User?
    suspend fun isAuthenticated() : Boolean

}