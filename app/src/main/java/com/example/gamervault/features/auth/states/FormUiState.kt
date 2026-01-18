package com.example.gamervault.features.auth.states

data class FormUiState(
    var hidePassword : Boolean = true,
    var errorEmail : String? = null,
    var errorPassword : String? = null
)
