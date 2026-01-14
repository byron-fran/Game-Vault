package com.example.gamervault.features.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gamervault.R
import com.example.gamervault.domain.models.Game
import com.example.gamervault.ui.components.BodyLarge
import com.example.gamervault.ui.components.BodyMedium
import com.example.gamervault.ui.components.IconStar

@Composable
fun ItemSearch(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(game.id) },
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = null,
                placeholder = painterResource(R.drawable.icon_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp).clip(
                    RoundedCornerShape(8.dp)
                )
            )
            Column {
                BodyLarge(game.name)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconStar(modifier = Modifier.size(16.dp), game.name)
                        BodyMedium(String.format("%.1f", game.rating))
                    }
                    game.released?.let {
                        BodyMedium(game.released.substringBefore("-"))
                    }
                }
            }
        }
    }
}