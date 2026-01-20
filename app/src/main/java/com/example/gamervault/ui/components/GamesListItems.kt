package com.example.gamervault.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamervault.domain.models.Game

@Composable
fun GamesListItems(
    games: List<Game>,
    modifier: Modifier = Modifier,
    action: @Composable (RowScope.(Game) -> Unit)? = null,
    onItemClick: (Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(350.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(games.size) { index ->
            val game = games[index]
            GameListItem( game, action = { action?.invoke(this, it) }) { id ->
                onItemClick(id)
            }
        }
    }
}