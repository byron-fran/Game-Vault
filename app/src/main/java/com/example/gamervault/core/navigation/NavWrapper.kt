package com.example.gamervault.core.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.gamervault.features.auth.screens.AccountRoute
import com.example.gamervault.features.auth.screens.SignInRoute
import com.example.gamervault.features.auth.screens.SignUpRoute
import com.example.gamervault.features.favorites.screens.FavoritesRoute
import com.example.gamervault.features.games.screens.GameDetailRoute
import com.example.gamervault.features.games.screens.GamesRoute
import com.example.gamervault.features.search.screens.SearchRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavWrapper(backStack: NavBackStack<NavKey>) {

    SharedTransitionLayout {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry(key = Route.GameScreen) {
                    GamesRoute { id ->
                        backStack.add(Route.GameDetailScreen(id))
                    }
                }
                entry<Route.GameDetailScreen> { args ->
                    GameDetailRoute(id = args.id) {
                        backStack.removeLastOrNull()
                    }
                }
                entry<Route.FavoritesScreen> {
                    FavoritesRoute { id ->
                        backStack.add(Route.GameDetailScreen(id))
                    }
                }
                entry<Route.SearchScreen> {
                    SearchRoute { id ->
                        backStack.add(Route.GameDetailScreen(id))
                    }
                }
                entry<Route.SignInScreen> {
                    SignInRoute { route ->
                        backStack.navigateFromAuthTo(route)
                    }
                }
                entry<Route.SignUpScreen> {
                    SignUpRoute { route ->
                        backStack.navigateFromAuthTo(route)
                    }
                }
                entry<Route.AccountScreen> {
                    AccountRoute { route ->
                        backStack.add(route)
                    }
                }
            }
        )
    }
}