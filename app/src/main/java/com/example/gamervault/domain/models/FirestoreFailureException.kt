package com.example.gamervault.domain.models

class FirestoreFailureException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
