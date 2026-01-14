package com.example.gamervault.features.search.states

sealed class SearchUiResponse {

    data object Loading : SearchUiResponse()
    data object Success : SearchUiResponse()
    data class Error(val message: String) : SearchUiResponse()

}