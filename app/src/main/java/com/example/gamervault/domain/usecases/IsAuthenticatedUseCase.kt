package com.example.gamervault.domain.usecases

import com.example.gamervault.domain.repositories.AuthRepository

class IsAuthenticatedUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.isAuthenticated()
}