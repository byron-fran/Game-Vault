package com.example.gamervault.core.navigation

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation3.runtime.NavKey
import com.example.gamervault.R
import com.example.gamervault.features.search.components.LabelSmall

data class NavSuiteItem(
    val route: Route,
    val label: Int,
    val icon: Int,
    val contentDescription: String? = null
)

val navSuiteItems = listOf(
    NavSuiteItem(
        Route.GameScreen,
        label = R.string.games,
        icon = R.drawable.icon_videogame,
        contentDescription = "games"
    ),
    NavSuiteItem(
        Route.FavoritesScreen,
        label = R.string.favorites,
        icon = R.drawable.icon_favorite,
        contentDescription = "favorites"
    ),
    NavSuiteItem(
        Route.SearchScreen,
        label = R.string.search,
        icon = R.drawable.icon_search,
        contentDescription = "search"
    )
)

@Composable
fun NavSuiteContainer(
    modifier: Modifier = Modifier,
    currentRoute: NavKey?,
    onClickItem: (Route) -> Unit,
    content: @Composable () -> Unit
) {

    val navItemColors = navigationBarColors()
    val navigationRailItemColors = navigationRailColors()
    val navigationDrawerItemColors = navigationDrawerColors()

    val windowSize = with(LocalDensity.current) {
        currentWindowSize().toSize().toDpSize()
    }

    val layoutType = if (windowSize.width >= 1200.dp) {
        NavigationSuiteType.NavigationDrawer
    } else {
        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
            adaptiveInfo = currentWindowAdaptiveInfo()
        )
    }

    NavigationSuiteScaffold(
        layoutType = layoutType,
        navigationSuiteItems = {
            navSuiteItems.forEach {  nav ->
                item(
                    selected = nav.route == currentRoute,
                    onClick = { onClickItem(nav.route) },
                    label = {
                        LabelSmall(
                            text = stringResource(nav.label), modifier = Modifier.offset(
                                y = if (layoutType == NavigationSuiteType.NavigationBar) -(8.dp) else 0.dp

                            ).testTag("${nav.label}"),
                            color = Color.Unspecified
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(nav.icon),
                            contentDescription = nav.contentDescription,
                        )
                    },
                    colors = NavigationSuiteItemColors(
                        navigationBarItemColors = navItemColors,
                        navigationRailItemColors = navigationRailItemColors,
                        navigationDrawerItemColors = navigationDrawerItemColors
                    ),
                )

            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
    ) {

        content()
    }
}

@Composable
fun navigationBarColors(): NavigationBarItemColors {

    return NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
        indicatorColor = Color.Transparent,
        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
        unselectedTextColor = MaterialTheme.colorScheme.onPrimary

    )
}

@Composable
fun navigationRailColors(): NavigationRailItemColors {
    return NavigationRailItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
        indicatorColor = Color.Transparent,
        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
        unselectedTextColor = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun navigationDrawerColors(): NavigationDrawerItemColors {

    return NavigationDrawerItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
        selectedBadgeColor = Color.Transparent,
        unselectedBadgeColor = Color.Transparent,
        selectedContainerColor = MaterialTheme.colorScheme.background
    )
}