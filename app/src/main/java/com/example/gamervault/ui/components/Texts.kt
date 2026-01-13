package com.example.gamervault.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun LabelSmall(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.labelSmall,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )

}

@Composable
fun BodyLarge(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.bodyLarge,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )

}

@Composable
fun BodyMedium(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.bodyMedium,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )

}
@Composable
fun BodySmall(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.bodySmall,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )

}
@Composable
fun TitleMedium(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.titleMedium,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )
}

@Composable
fun TitleLarge(
    text: String,
    color : Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = MaterialTheme.typography.titleLarge,
        color = color,
        overflow = textOverflow,
        textAlign = textAlign
    )
}