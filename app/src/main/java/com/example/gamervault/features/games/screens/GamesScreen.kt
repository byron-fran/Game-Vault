package com.example.gamervault.features.games.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gamervault.domain.models.Game
import com.example.gamervault.features.games.components.GameCard
import com.example.gamervault.features.games.viewmodel.GamesViewModel
import com.example.gamervault.ui.screens.EmptyScreen
import com.example.gamervault.ui.screens.LoadingScreen

@Composable
fun GamesRoute (
    gamesViewModel: GamesViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit
) {

    val lazyPagingItems = gamesViewModel.gameList.collectAsLazyPagingItems()

    GamesScreen(
        lazyPagingItems = lazyPagingItems,
        onNavigateToDetail = onNavigateToDetail
    )

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GamesScreen(
    lazyPagingItems : LazyPagingItems<Game>,
    onNavigateToDetail: (Int) -> Unit
) {

    Column(modifier = Modifier.statusBarsPadding()) {

        when (lazyPagingItems.loadState.refresh) {
            is LoadState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is LoadState.Error -> EmptyScreen(modifier = Modifier.fillMaxSize()) {
                val error = lazyPagingItems.loadState.refresh as LoadState.Error
                Text("Error loading games: ${error.error.message ?: "Unknown error"}")
            }

            is LoadState.NotLoading -> {
                if (lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.append.endOfPaginationReached && lazyPagingItems.loadState.append !is LoadState.Loading) {

                    EmptyScreen(modifier = Modifier.fillMaxSize()) {
                        Text("No games found")
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(170.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        items(lazyPagingItems.itemCount) { position ->
                            val item = lazyPagingItems[position]
                            item?.let { game ->
                                GameCard(
                                    game = game,
                                    modifier = Modifier
                                ) {
                                    onNavigateToDetail(game.id)
                                }
                            }
                        }
                        lazyPagingItems.loadState.append.let { state ->
                            when (state) {
                                is LoadState.Loading -> {
                                    item {
                                        LoadingScreen( modifier = Modifier.fillMaxWidth().padding(16.dp))
                                    }
                                }
                                is LoadState.Error -> {
                                    item {
                                        EmptyScreen( modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                                            Text("Error loading games: ${state.error.message ?: "Unknown error"}")
                                        }
                                    }
                                }
                                is LoadState.NotLoading -> Unit
                            }
                        }
                    }
                }
            }
        }
    }
}