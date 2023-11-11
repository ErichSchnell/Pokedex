package com.jereschneider.pokedex.domain.models

data class PokemonModel(
    val id: Int,
    val name: String,
    val urlImg: String,
    val types: List<String>
)
