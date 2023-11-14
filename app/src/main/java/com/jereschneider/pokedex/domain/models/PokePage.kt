package com.jereschneider.pokedex.domain.models

data class PokePage(
    val nextPage: String?,
    val previousPage: String?,
    val pokemons: List<PokemonRaw>
)
