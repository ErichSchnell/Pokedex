package com.jereschneider.pokedex.ui.models

import com.jereschneider.pokedex.domain.models.PokemonDetailModel

data class HomeState(
    val listPokemons: ComponentState = ComponentState.Loading,
    val favPokemons: ComponentState = ComponentState.Loading,
    val isLoadingFetch: Boolean = false
)

sealed class ComponentState {
    data object Loading : ComponentState()
    data class Success(val pokemons: List<PokemonDetailModel> = emptyList()) : ComponentState()
    data class Error(val message: String) : ComponentState()
}