package com.example.gamervault.features.search.events

sealed class SearchEvents {

    data object OnSearch : SearchEvents()
    data object ClearSearch : SearchEvents()
    data class OnValueChange(val value: String) : SearchEvents()
    data object OnBackSpace : SearchEvents()
}