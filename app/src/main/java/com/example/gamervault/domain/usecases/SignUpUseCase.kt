package com.example.gamervault.domain.usecases

import com.example.gamervault.domain.repositories.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String, username: String?) = repository.signUp(email, password, username)

}