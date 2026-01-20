package com.example.gamervault.features.favorites.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamervault.core.mappers.toFavorite
import com.example.gamervault.domain.models.FirestoreFailureException
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.usecases.favorites.DeleteFavoriteByIdUseCase
import com.example.gamervault.domain.usecases.favorites.GetFavoritesUseCase
import com.example.gamervault.domain.usecases.favorites.InsertFavoriteUseCase
import com.example.gamervault.features.favorites.states.FavoritesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteByIdUseCase: DeleteFavoriteByIdUseCase
) : ViewModel() {

    private var _favoritesUiState = mutableStateOf(FavoritesUiState())
    val favoritesUiState = _favoritesUiState

    private var _favoriteSelected = mutableStateOf<Game?>(null)
    val favoriteSelected = _favoriteSelected

    init {
       getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            _favoritesUiState.value = _favoritesUiState.value.copy(isLoading = true)
            try {
                val favorites = getFavoritesUseCase()
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    favorites = favorites,
                    isLoading = false
                )
            } catch (e: FirestoreFailureException) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            } catch (e: Exception) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun insertFavorite(game: Game) {

        viewModelScope.launch(Dispatchers.IO) {
            _favoritesUiState.value = _favoritesUiState.value.copy(isFavoriteLoading = true)
            try {
                async { insertFavoriteUseCase(game.toFavorite()) }.await()
                val favoritesDeferred = async { getFavoritesUseCase() }
                val favorites = favoritesDeferred.await()
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    favorites = favorites
                )
            } catch (e: FirestoreFailureException) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    error = e.message
                )
            } catch (e: Exception) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    error = e.message
                )
            }

        }
    }

    private fun deleteFavorite(favoriteId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _favoritesUiState.value = _favoritesUiState.value.copy(isFavoriteLoading = true)
            try {
                async { deleteFavoriteByIdUseCase(favoriteId) }.await()
                val favoritesDeferred = async { getFavoritesUseCase() }
                val favorites = favoritesDeferred.await()
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    favorites = favorites
                )
            } catch (e: FirestoreFailureException) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    error = e.message
                )
            } catch (e: Exception) {
                _favoritesUiState.value = _favoritesUiState.value.copy(
                    isFavoriteLoading = false,
                    error = e.message
                )

            }
        }
    }

    fun isFavorite(id: String): Boolean {
        val favorites = favoritesUiState.value.favorites
        return favorites.find { it.gameId == id } != null
    }

    fun onClickFavorite(game: Game) {
        val id = game.id.toString()
        if (isFavorite(id)) deleteFavorite(id)
        else insertFavorite(game)
    }

    fun onFavoriteSelected(game : Game?) {
        _favoriteSelected.value = game
    }

    fun clearError() {
        _favoritesUiState.value = _favoritesUiState.value.copy(error = null)
    }
}