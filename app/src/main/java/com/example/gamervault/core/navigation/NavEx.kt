package com.example.gamervault.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.bottomBarNavigationFlat(key: NavKey) {

    if(isEmpty()) return

    val first = first()
    if (key == first) {
        while (size > 1) removeLastOrNull()
        return
    }
    if(key != lastOrNull()){
        if(size > 1) {
            clear()
            add(0, first)
            add(key)
        }
        else add(key)
    }
}

fun NavBackStack<NavKey>.navigateFromAuthTo(key: NavKey) {

    when (key) {
        is Route.GameScreen -> {
            clear()
            add(key)
        }
        is Route.SignUpScreen -> {
            removeLastOrNull()
            add(key)
        }
        is Route.SignInScreen -> {
            removeLastOrNull()
            add(key)
        }
        else -> add(key)
    }
}