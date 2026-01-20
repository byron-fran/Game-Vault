package com.example.gamervault.features.favorites.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gamervault.R
import com.example.gamervault.domain.models.Game
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.CustomIcon
import com.example.gamervault.ui.components.TitleMedium

@Composable
fun FavoriteActions(
    game: Game,
    modifier: Modifier = Modifier,
    onClickFavorite: (Game) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        TitleMedium(
            text = game.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp).clickable { onClickFavorite(game) }
        ) {
            CustomIcon( icon = R.drawable.icon_favorite,)
            BodyLarge(stringResource(R.string.remove_favorite))
        }
    }
}