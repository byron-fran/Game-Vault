package com.example.gamervault.features.search.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamervault.domain.usecases.SearchGamesUseCase
import com.example.gamervault.features.search.events.SearchEvents
import com.example.gamervault.features.search.states.SearchUiResponse
import com.example.gamervault.features.search.states.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase
) : ViewModel() {
    private var _searchUiState = mutableStateOf(SearchUiState())
    val searchUiState = _searchUiState
    private var _searchUiResponse = mutableStateOf<SearchUiResponse>(SearchUiResponse.Success)
    val searchUiResponse = _searchUiResponse

    val job: Job? = null

    private fun onSearch() {
        job?.cancel()
        viewModelScope.launch {
            try {
                delay(700)
                _searchUiResponse.value = SearchUiResponse.Loading
                val result = searchGamesUseCase(_searchUiState.value.query)
                _searchUiState.value = _searchUiState.value.copy(games = result)
                _searchUiResponse.value = SearchUiResponse.Success

            } catch (e: Exception) {
                _searchUiResponse.value =
                    SearchUiResponse.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun onClear() {
        _searchUiState.value = _searchUiState.value.copy(
            query = "",
            expanded = false,
            games = emptyList()
        )
    }

    fun onValueChange(value: String) {
        _searchUiState.value = _searchUiState.value.copy(query = value)
        if (_searchUiState.value.query.trim().length >= 2) {
            _searchUiState.value = _searchUiState.value.copy( expanded = true)
            onSearch()
        }
        else {
            _searchUiState.value = _searchUiState.value.copy(expanded = false, games = emptyList(),)
        }
    }

    fun onBackSpace() {
        _searchUiState.value = _searchUiState.value.copy(
            query = _searchUiState.value.query.dropLast(1)
        )
        if (_searchUiState.value.query.trim().length >= 2) {
            _searchUiState.value = _searchUiState.value.copy(expanded = true)
            onSearch()
        }else {
            _searchUiState.value = _searchUiState.value.copy(expanded = false, games = emptyList(), )
        }
    }

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.OnSearch -> onSearch()
            is SearchEvents.ClearSearch -> onClear()
            is SearchEvents.OnValueChange -> onValueChange(event.value)
            is SearchEvents.OnBackSpace -> onBackSpace()

        }
    }
}