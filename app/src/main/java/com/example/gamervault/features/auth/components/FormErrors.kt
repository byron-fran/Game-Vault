package com.example.gamervault.features.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamervault.features.auth.states.AuthUiState
import com.example.gamervault.features.auth.states.FormUiState
import com.example.gamervault.ui.components.TextError

@Composable
fun FormErrors(
    modifier: Modifier = Modifier,
    formUiState: FormUiState,
    authUiState: AuthUiState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        formUiState.errorEmail?.let {
            TextError(it, modifier = Modifier.fillMaxWidth())
        }
        formUiState.errorPassword?.let {
            TextError(it, modifier = Modifier.fillMaxWidth())
        }
        authUiState.isError?.let {
            TextError(it, modifier = Modifier.fillMaxWidth())
        }
    }
}