package com.jereschneider.pokedex.domain.models

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val pokemons: List<PokemonModel>, var currentPage: Int = 0) : HomeState()
    data class Error(val message: String) : HomeState()
}