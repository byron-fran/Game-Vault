package com.example.gamervault.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gamervault.R

@Composable
fun IconStar(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.onTertiary
) {

    Icon(
        painter = painterResource(R.drawable.icon_star),
        contentDescription = "icon_start_${contentDescription}",
        tint = tint,
        modifier = modifier
    )
}

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    contentDescription: String? = null,
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = "icon_${contentDescription}",
        tint = tint,
        modifier = modifier.size(24.dp)
    )
}

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    icon: Int,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick, modifier = modifier) {
        CustomIcon(icon = icon, tint = tint, contentDescription = contentDescription)
    }

}