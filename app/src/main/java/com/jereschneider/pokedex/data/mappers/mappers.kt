package com.jereschneider.pokedex.data.mappers

import com.jereschneider.pokedex.data.models.PokePageDto
import com.jereschneider.pokedex.data.models.PokemonDetailDto
import com.jereschneider.pokedex.data.models.Type
import com.jereschneider.pokedex.domain.models.About
import com.jereschneider.pokedex.domain.models.PokePage
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel

fun PokemonDetailDto.toPokemonDetailModel() : PokemonDetailModel {
    val pokemon = PokemonModel(
        id = id,
        name = name,
        urlImg = sprites.other?.officialArtwork?.frontDefault ?: this.sprites.frontDefault,
        types = types.map { it.toTypeString() }
    )
    val about = About(
        species = species.name,
        height = height.toString(),
        weight = weight.toString(),
        abilities = abilities.toString()
    )
    return PokemonDetailModel(
        pokemon = pokemon,
        about = about
    )
}

fun Type.toTypeString() = this.type.name

fun PokePageDto.toPokePage() : PokePage = PokePage(
    nextPage = this.next,
    previousPage = this.previous,
    pokemons = this.results
)
