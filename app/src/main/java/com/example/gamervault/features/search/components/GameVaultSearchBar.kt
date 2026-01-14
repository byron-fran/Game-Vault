package com.example.gamervault.features.search.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamervault.R
import com.example.gamervault.ui.components.CustomIconButton
import com.example.gamervault.ui.components.LabelMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameVaultSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    expanded : Boolean ,
    onValueChange: (String) -> Unit,
    onClickBackSpace: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    SearchBar(
        inputField = {
            TextField(
                value = value,
                onValueChange = onValueChange,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    CustomIconButton (icon = R.drawable.icon_outline_backspace){
                        onClickBackSpace()
                    }
                },
                leadingIcon = {},
                maxLines = 1,
                placeholder = {
                    LabelMedium(stringResource(R.string.search_game), color = MaterialTheme.colorScheme.onSurface)
                },
                modifier = Modifier.testTag("search_bar_input")

            )
        },
        onExpandedChange = {},
        expanded = expanded,
        modifier = modifier.testTag("search_bar"),
        colors = SearchBarDefaults.colors(
            dividerColor = Color.Transparent,
            inputFieldColors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            ),
            containerColor = MaterialTheme.colorScheme.background


        )
    ) {
        content()
    }
}