package com.example.gamervault.core.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.gamervault.features.auth.screens.AccountScreen
import com.example.gamervault.features.auth.screens.SignInScreen
import com.example.gamervault.features.auth.screens.SignUpScreen
import com.example.gamervault.features.favorites.screens.FavoritesScreen
import com.example.gamervault.features.games.screens.GameDetailScreen
import com.example.gamervault.features.games.screens.GamesScreen
import com.example.gamervault.features.games.viewmodel.GamesViewModel
import com.example.gamervault.features.search.screens.SearchScreen
import com.example.gamervault.features.search.viewmodel.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavWrapper(backStack: NavBackStack<NavKey>) {


    SharedTransitionLayout {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry(key = Route.GameScreen) {
                    GamesScreen { id ->
                        backStack.add(Route.GameDetailScreen(id))
                    }
                }
                entry<Route.GameDetailScreen> { args ->
                    val gameViewModel: GamesViewModel = hiltViewModel()
                    GameDetailScreen(
                        id = args.id,
                        onEvent = gameViewModel::onEvent,
                        gamesUiState = gameViewModel.gameUiState.value,
                        gameUiStatus = gameViewModel.gameUiStatus.value
                    ) {
                        backStack.removeLastOrNull()
                    }
                }
                entry<Route.FavoritesScreen> {
                    FavoritesScreen()
                }
                entry<Route.SearchScreen> {
                    val searchViewModel: SearchViewModel = hiltViewModel()
                    SearchScreen(
                        searchUiState = searchViewModel.searchUiState.value,
                        searchUiResponse = searchViewModel.searchUiResponse.value,
                        onEvent = searchViewModel::onEvent
                    ) { id ->
                        backStack.add(Route.GameDetailScreen(id))
                    }
                }
                entry<Route.SignInScreen> {
                    SignInScreen { route ->
                        backStack.navigateFromAuthTo(route)
                    }
                }
                entry<Route.SignUpScreen> {
                    SignUpScreen { route ->
                        backStack.navigateFromAuthTo(route)
                    }
                }
                entry<Route.AccountScreen> {
                    AccountScreen { route ->
                        backStack.add(route)
                    }
                }
            }
        )
    }
}