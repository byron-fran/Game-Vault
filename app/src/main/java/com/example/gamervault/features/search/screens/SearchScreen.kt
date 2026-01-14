package com.example.gamervault.features.search.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gamervault.R
import com.example.gamervault.features.search.components.GameVaultSearchBar
import com.example.gamervault.features.search.components.ItemsSearchList
import com.example.gamervault.features.search.events.SearchEvents
import com.example.gamervault.features.search.states.SearchUiResponse
import com.example.gamervault.features.search.states.SearchUiState
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.screens.EmptyScreen
import com.example.gamervault.ui.screens.LoadingScreen

@Composable
fun SearchScreen(
    searchUiState: SearchUiState,
    searchUiResponse: SearchUiResponse,
    onEvent : (SearchEvents) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {


    Column( modifier = Modifier.statusBarsPadding()) {
        GameVaultSearchBar(
            value = searchUiState.query,
            onClickBackSpace = { onEvent(SearchEvents.OnBackSpace) },
            onValueChange = {
                onEvent(SearchEvents.OnValueChange(it))
            },
            expanded = searchUiState.expanded,
            modifier = Modifier.fillMaxWidth()
        ) {

            when(searchUiResponse) {
                SearchUiResponse.Loading -> {
                    LoadingScreen(modifier = Modifier.fillMaxSize())
                }
                SearchUiResponse.Success -> {
                    if(searchUiState.games.isNotEmpty()) {
                        ItemsSearchList(searchUiState.games) {
                            onNavigateToDetail(it)
                        }
                    }
                    else{
                        EmptyScreen(modifier = Modifier.fillMaxSize()) {
                            BodyLarge(stringResource(R.string.no_games_found))
                        }
                    }
                }
                is SearchUiResponse.Error -> {
                    EmptyScreen(modifier = Modifier.fillMaxSize()) {
                        BodyLarge(text = searchUiResponse.message)
                    }
                }
            }
        }
        if(!searchUiState.expanded) {
            EmptyScreen(modifier = Modifier.fillMaxSize()) {
                BodyLarge(stringResource(R.string.search_your_favorite_game))
            }
        }
    }
}