package com.jereschneider.pokedex.ui.models

import com.jereschneider.pokedex.domain.models.PokemonDetailModel

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val pokemons: List<PokemonDetailModel> = emptyList()) : HomeState()
    data class Error(val message: String) : HomeState()
}