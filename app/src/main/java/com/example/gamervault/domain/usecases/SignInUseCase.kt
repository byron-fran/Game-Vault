package com.example.gamervault.domain.usecases

import com.example.gamervault.domain.repositories.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.signIn(email, password)
}