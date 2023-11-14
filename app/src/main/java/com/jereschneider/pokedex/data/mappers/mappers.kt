package com.jereschneider.pokedex.data.mappers

import com.jereschneider.pokedex.data.models.PokePageDto
import com.jereschneider.pokedex.data.models.PokemonDetailDto
import com.jereschneider.pokedex.data.models.Type
import com.jereschneider.pokedex.domain.models.PokePage
import com.jereschneider.pokedex.domain.models.PokemonModel

fun PokemonDetailDto.toPokemonModel() : PokemonModel = PokemonModel(
    id = this.id,
    name = this.name,
    urlImg = this.sprites.other?.officialArtwork?.frontDefault ?: this.sprites.frontDefault,
    types = this.types.map { it.toTypeString() }
)

fun Type.toTypeString() = this.type.name

fun PokePageDto.toPokePage() : PokePage = PokePage(
    nextPage = this.next,
    previousPage = this.previous,
    pokemons = this.results
)
