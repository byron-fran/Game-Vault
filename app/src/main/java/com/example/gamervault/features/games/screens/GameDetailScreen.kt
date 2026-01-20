package com.example.gamervault.features.games.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.gamervault.R
import com.example.gamervault.domain.models.Game
import com.example.gamervault.domain.models.PlatformX
import com.example.gamervault.features.auth.viewmodel.AuthViewModel
import com.example.gamervault.features.favorites.viewmodel.FavoritesViewModel
import com.example.gamervault.features.games.components.GameInfo
import com.example.gamervault.features.games.events.GameUiEvents
import com.example.gamervault.features.games.states.GameUIStatus
import com.example.gamervault.features.games.states.GameUiState
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.BodySmall
import com.example.gamervault.ui.components.CustomIcon
import com.example.gamervault.ui.components.CustomIconButton
import com.example.gamervault.ui.components.IconStar
import com.example.gamervault.ui.components.TitleLarge
import com.example.gamervault.ui.screens.EmptyScreen
import com.example.gamervault.ui.screens.LoadingScreen

@Composable
fun GameDetailScreen(
    favoritesViewModel: FavoritesViewModel,
    authViewModel: AuthViewModel,
    id: Int,
    onEvent: (GameUiEvents) -> Unit,
    gamesUiState: GameUiState,
    gameUiStatus: GameUIStatus,
    onNavigateBack: () -> Unit
) {

    val game = gamesUiState.gameDetail
    val colorOnPrimary = Color(0xFFcad5e2)
    val scrollState = rememberScrollState()
    val favoritesUiState by favoritesViewModel.favoritesUiState
    val authUiState by authViewModel.authUiState

    LaunchedEffect(key1 = id) {
        if (id != 0) {
            onEvent(GameUiEvents.GetGameById(id))
        }
    }
    LaunchedEffect(authUiState.isAuthenticated) {
        if(authUiState.isAuthenticated){
            favoritesViewModel.getFavorites()
        }
    }

    DisposableEffect(Unit) {
        onDispose { onEvent(GameUiEvents.ClearGameDetail) }
    }

    when (gameUiStatus) {
        is GameUIStatus.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is GameUIStatus.Success -> {

            Box(modifier = Modifier.fillMaxSize()) {
                CustomIconButton(
                    icon = R.drawable.icon_outline_arrow_back,
                    modifier = Modifier.statusBarsPadding().align(Alignment.TopStart).padding(start = 8.dp).zIndex(1f)
                ) {
                    onNavigateBack()
                }
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    game?.let {
                        Box {

                            AsyncImage(
                                model = game.backgroundImage,
                                contentDescription = "image_${game.name}",
                                modifier = Modifier.aspectRatio(1f),
                                placeholder = painterResource(R.drawable.icon_image),
                                contentScale = ContentScale.Crop
                            )
                            GameInfo(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                name = {
                                    TitleLarge(
                                        text = game.name,
                                        color = colorOnPrimary, maxLines = 3,
                                        modifier = Modifier.weight(1f)
                                    )
                                },
                                icon = {
                                    IconStar(modifier = Modifier.size(24.dp), game.name)
                                },
                                rating = {
                                    BodyLarge(
                                        text = String.format("%.1f", game.rating),
                                        color = colorOnPrimary
                                    )
                                },
                                released = {
                                    game.released?.let {
                                        BodyLarge(
                                            text = game.released.substringBefore("-"),
                                            color = colorOnPrimary
                                        )
                                    }
                                }
                            )
                        }
                        GameDetailBody(
                            isAuthenticated = authUiState.isAuthenticated,
                            game = game,
                            isLoading = favoritesUiState.isFavoriteLoading,
                            modifier = Modifier.padding(4.dp),
                            onClickFavorite = favoritesViewModel::onClickFavorite,
                            isFavorite = favoritesViewModel::isFavorite
                        )
                    }
                }
            }

        }

        is GameUIStatus.Error -> {
            EmptyScreen(
                modifier = Modifier.fillMaxSize().statusBarsPadding()
            ) {
                BodyLarge("Error Loading Game ${gameUiStatus.message}", maxLines = 3)
            }
        }
    }

}

@Composable
fun GameDetailBody(
    isAuthenticated: Boolean,
    game: Game,
    modifier: Modifier = Modifier,
    isFavorite: (String) -> Boolean,
    isLoading: Boolean,
    onClickFavorite: (Game) -> Unit
) {
    var showLines by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CustomIconButton(
                icon = if (isFavorite(game.id.toString()) && isAuthenticated) R.drawable.icon_favorite else R.drawable.icon_favorite_outline,
                enabled = !isLoading,
            ) {
                onClickFavorite(game)
            }
        }
        BodyLarge("About Game")
        GameDetailDescription(
            description = game.description.trimIndent(),
            showMaxLines = showLines,
            modifier = Modifier.animateContentSize().clickable { showLines = !showLines }
        )
        BodyLarge("Tags")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            game.tags.forEach { Chip(it.name) }
        }
        BodyLarge("Platforms")
        GameDetailPlatforms(platforms = game.platforms)
        BodyLarge("Genres")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            game.genres.forEach { Chip(it.name) }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }

}

@Composable
fun GameDetailDescription(
    description: String,
    modifier: Modifier = Modifier,
    showMaxLines: Boolean
) {
    Text(
        text = AnnotatedString.fromHtml(
            htmlString = description,
        ),
        maxLines = if (showMaxLines) 10 else Int.MAX_VALUE,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Chip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        BodySmall(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(8.dp)

        )
    }
}

@Composable
fun GameDetailPlatforms(
    platforms: List<PlatformX>,
    modifier: Modifier = Modifier
) {
    platforms.forEach { platformX ->
        Column {
            Row(
                modifier = modifier.padding(vertical = 8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when {
                        platformX.platform.name.contains("playstation", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_playstation, contentDescription = "playstation")
                        }

                        platformX.platform.name.contains("xbox", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_xbox, contentDescription = "xbox")
                        }

                        platformX.platform.name.contains("pc", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_laptop_windows, contentDescription = "pc")
                        }

                        platformX.platform.name.contains("nintendo", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_nintendo, contentDescription = "nintendo")
                        }

                        platformX.platform.name.contains("android", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_android, contentDescription = "android")
                        }

                        platformX.platform.name.contains("ios", ignoreCase = true) -> {
                            CustomIcon(icon = R.drawable.icon_ios, contentDescription = "ios")
                        }
                    }
                    BodySmall(platformX.platform.name)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BodySmall("Released")
                    BodySmall(platformX.releasedAt.substringBefore("-"))
                }
            }
        }
    }
}