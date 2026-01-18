package com.example.gamervault.features.auth.states

data class FormUiState(
    var hidePassword : Boolean = true,
    var errorEmail : String? = null,
    var errorPassword : String? = null,
    var readOnlyEmail : Boolean = false,
    var readOnlyUsername : Boolean = false,
    var readOnlyPassword : Boolean = false
)
