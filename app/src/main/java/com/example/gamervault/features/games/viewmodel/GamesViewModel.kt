package com.example.gamervault.features.games.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gamervault.domain.usecases.GetGameByIdUseCase
import com.example.gamervault.domain.usecases.GetGamesUseCase
import com.example.gamervault.features.games.events.GameUiEvents
import com.example.gamervault.features.games.states.GameUIStatus
import com.example.gamervault.features.games.states.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    getGamesUseCase: GetGamesUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase
) : ViewModel() {

    private var _gameUiState = mutableStateOf(GameUiState())
    val gameUiState = _gameUiState
    private var _gameUiStatus = mutableStateOf<GameUIStatus>(GameUIStatus.Loading)
    val gameUiStatus = _gameUiStatus


    val gameList = getGamesUseCase()
        .catch { _gameUiStatus.value = GameUIStatus.Error(it.message ?: "Something went wrong") }
        .cachedIn(viewModelScope)

    fun getGameById(id: Int) {
        viewModelScope.launch {

            _gameUiStatus.value = GameUIStatus.Loading
            try {
                val result = getGameByIdUseCase(id)
                _gameUiState.value = _gameUiState.value.copy(gameDetail = result)
                _gameUiStatus.value = GameUIStatus.Success

            } catch (e: Exception) {
                _gameUiStatus.value = GameUIStatus.Error(e.message ?: "Something went wrong")
            }
        }
    }

    fun clearGameDetail() {
        _gameUiState.value = _gameUiState.value.copy(gameDetail = null)
    }

    fun onEvent(event: GameUiEvents) {
        when (event) {
            is GameUiEvents.GetGameById -> getGameById(event.id)
            is GameUiEvents.ClearGameDetail -> clearGameDetail()
        }
    }

}