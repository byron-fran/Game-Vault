package com.example.gamervault.features.games.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.gamervault.R
import com.example.gamervault.domain.models.Game
import com.example.gamervault.features.search.components.BodyLarge
import com.example.gamervault.features.search.components.BodyMedium
import com.example.gamervault.ui.components.IconStar

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val colorOnPrimary = Color(0xFFe0e7ff)

    Box(modifier = modifier
        .clickable { onClick() }
        .clip(shape = RoundedCornerShape(8.dp))) {
        SubcomposeAsyncImage(
            model = game.backgroundImage,
            contentDescription = "${game.name}_image_subCompose",
            modifier = Modifier.aspectRatio(1f),
            contentScale = ContentScale.Crop,
            loading = {
                Image(
                    painter = painterResource(R.drawable.icon_image),
                    contentDescription = "image_${game.name}",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .testTag("image_${game.name}"),
                    contentScale = ContentScale.Crop
                )
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.icon_no_image),
                    contentDescription = "no_image_${game.name}",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .testTag("no_image_${game.name}"),
                    contentScale = ContentScale.Crop
                )
            },
            success = {
                SubcomposeAsyncImageContent()
            }
        )
        GameInfo(
            modifier = Modifier.align(Alignment.BottomCenter),
            name = {
                BodyLarge(text = game.name, color = colorOnPrimary, maxLines = 2, modifier = Modifier.weight(1f))
            },
            rating = {
                BodyMedium( String.format("%.1f", game.rating), color = colorOnPrimary)
            },
            released = {
                BodyMedium(game.released.substringBefore("-"), color = colorOnPrimary)
            },
            icon = {
                IconStar(modifier = Modifier.size(16.dp), game.name)
            }
        )
    }
}

@Composable
fun GameInfo(
    modifier: Modifier = Modifier,
    name: @Composable RowScope.() -> Unit,
    rating: @Composable () -> Unit,
    released: @Composable () -> Unit,
    icon : @Composable () -> Unit

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.5f),
                        Color.Black.copy(alpha = 0.7f),
                        Color.Black
                    )
                )
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        name()
        Spacer(modifier = Modifier.width(2.dp))
        Column(horizontalAlignment = Alignment.End) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                icon()
                rating()
            }
            released()
        }
    }
}