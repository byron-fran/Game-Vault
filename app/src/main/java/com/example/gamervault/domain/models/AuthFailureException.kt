package com.example.gamervault.domain.models

 class AuthFailureException(
     message: String,
    cause: Throwable? = null
) : Exception(message, cause)