package com.example.gamervault.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.gamervault.core.navigation.NavSuiteContainer
import com.example.gamervault.core.navigation.NavWrapper
import com.example.gamervault.core.navigation.Route
import com.example.gamervault.ui.theme.GamerVaultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamerVaultTheme {
                val backStack = rememberNavBackStack(Route.GameScreen)
                val currentRoute = remember {
                    derivedStateOf {
                        backStack.lastOrNull()
                    }
                }
                NavSuiteContainer(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    currentRoute = currentRoute.value,
                    onClickItem = { backStack.add(it)}
                    ) {
                    NavWrapper(backStack = backStack)
                }
            }
        }
    }
}