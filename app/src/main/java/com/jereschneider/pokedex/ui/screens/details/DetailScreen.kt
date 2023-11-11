package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel

@Composable
fun DetailScreen() {
    val pokemonModel = PokemonModel(
        id = 0,
        name = "bulbasaur",
        types = listOf("grass","poison"),
        urlImg = ""
    )
    val about = About(
        species = "Seed",
        height = "2`3.6\" (0.70 cm)",
        weight = "11.2 lbs (6.9 kg)",
        abilities = "Overgrow, Chlorophyl",
        gender = "???",
        eggGroup = "Monster",
        eggCycle = "Grass"
    )
    val detailPokemon = PokemonDetailModel(pokemonModel, about)
    DetailContent(detailPokemon = detailPokemon, onBackClick = { }, onSubscribe = {})
}