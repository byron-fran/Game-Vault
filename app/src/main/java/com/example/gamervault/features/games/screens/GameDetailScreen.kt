package com.example.gamervault.features.games.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.gamervault.features.games.events.GameUiEvents
import com.example.gamervault.features.games.states.GameUIStatus
import com.example.gamervault.features.games.states.GameUiState

@Composable
fun GameDetailScreen(
    id: Int,
    onEvent: (GameUiEvents) -> Unit,
    gamesUiState: GameUiState,
    gameUiStatus: GameUIStatus

) {

    val game = gamesUiState.gameDetail

    LaunchedEffect(key1 = id) {
        if (id != 0) {
            onEvent(GameUiEvents.GetGameById(id))
        }
    }

    DisposableEffect(Unit) {
        onDispose { onEvent(GameUiEvents.ClearGameDetail) }
    }

    Column( modifier = Modifier.statusBarsPadding()) {
        // TODO Add more content
    }
}