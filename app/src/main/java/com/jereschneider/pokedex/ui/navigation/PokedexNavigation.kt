package com.jereschneider.pokedex.ui.navigation

sealed class PokedexNavigation(val route: String) {
    data object Home : PokedexNavigation(route = "pokedex_home")
    data object Detail : PokedexNavigation(route = "pokedex_detail")
}

enum class PokeNavArgs(val key: String) {
    PokemonDetail("pokemon_detail")
}