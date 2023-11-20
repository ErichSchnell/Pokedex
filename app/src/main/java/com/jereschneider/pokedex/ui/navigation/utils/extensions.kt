package com.jereschneider.pokedex.ui.navigation.utils

import androidx.navigation.NavHostController

fun<T: Any> NavHostController.navigateTo(
    route: String,
    key: String = "default_key",
    value: T? = null,
) {
    this.currentBackStackEntry?.savedStateHandle?.set(key, value)
    this.navigate(route)
}

fun<T> NavHostController.parcelable(key: String = "default_key"): T? {
    return this.previousBackStackEntry?.savedStateHandle?.get<T>(key)
}