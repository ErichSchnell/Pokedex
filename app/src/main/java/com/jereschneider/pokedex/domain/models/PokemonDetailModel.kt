package com.jereschneider.pokedex.domain.models

data class PokemonDetailModel(
    val pokemon: PokemonModel,
    val about: About,
)

data class About(
    val species: String,
    val height: String,
    val weight: String,
    val abilities: String,
    val gender: String,
    val eggGroup: String,
    val eggCycle: String,
)
