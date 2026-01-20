package com.example.gamervault.features.favorites.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamervault.R
import com.example.gamervault.core.mappers.toGame
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.favorites.components.FavoriteActions
import com.example.gamervault.features.favorites.viewmodel.FavoritesViewModel
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.CustomIconButton
import com.example.gamervault.ui.components.CustomModalBottomSheet
import com.example.gamervault.ui.components.GamesListItems
import com.example.gamervault.ui.screens.EmptyScreen
import com.example.gamervault.ui.screens.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    authViewModel: AuthViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val favoritesUiState by favoritesViewModel.favoritesUiState
    val favoriteSelected by favoritesViewModel.favoriteSelected
    val authUiState by authViewModel.authUiState

    LaunchedEffect(authUiState.isAuthenticated) {
        if(authUiState.isAuthenticated){
            favoritesViewModel.getFavorites()
        }
    }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        when {
            !authUiState.isAuthenticated -> {
                EmptyScreen(modifier = Modifier.fillMaxSize()) {
                    BodyLarge(text = stringResource(R.string.signin_or_create_account), maxLines = 3)
                }
            }
            favoritesUiState.isLoading -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }
            favoritesUiState.error != null -> {
                EmptyScreen(modifier = Modifier.fillMaxSize()) {
                    BodyLarge(text = favoritesUiState.error ?: "", maxLines = 3)
                }
            }

            favoritesUiState.favorites.isEmpty() -> {
                EmptyScreen(modifier = Modifier.fillMaxSize()) {
                    BodyLarge(text = stringResource(R.string.no_favorites), maxLines = 3)
                }
            }
            else -> {
                val games = favoritesUiState.favorites.map { it.toGame() }
                GamesListItems(
                    games,
                    action = { game ->
                        CustomIconButton(icon = R.drawable.icon_more_vert) {
                            favoritesViewModel.onFavoriteSelected(game)
                        }
                    }
                ) {
                    onNavigateToDetail(it)
                }
                favoriteSelected?.let { game ->
                    CustomModalBottomSheet(
                        modifier = Modifier,
                        onDismissRequest = {
                            favoritesViewModel.onFavoriteSelected(null)
                        }
                    ) {
                        FavoriteActions(
                            game = game,
                            modifier = Modifier.height(400.dp)
                        ) {
                            favoritesViewModel.onClickFavorite(game)
                            favoritesViewModel.onFavoriteSelected(null)
                        }
                    }
                }
            }
        }
    }
}