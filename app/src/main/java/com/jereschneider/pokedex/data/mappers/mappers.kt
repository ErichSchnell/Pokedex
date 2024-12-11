package com.jereschneider.pokedex.data.mappers

import com.jereschneider.pokedex.data.database.PokemonDetailEntity
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
        urlImg = sprites.other?.officialArtwork?.frontDefault ?: this.sprites.frontDefault.orEmpty(),
        types = types.map { it.toTypeString() },
        isFav = false
    )
    val about = About(
        species = species.name,
        height = height.toString(),
        weight = weight.toString(),
        abilities = abilities.map { it.ability.name }.toString(),
        stats = stats.map { it.baseStat }
    )
    return PokemonDetailModel(
        pokemon = pokemon,
        about = about
    )
}

fun PokemonDetailModel.toEntity() = PokemonDetailEntity(
    id = this.pokemon.id,
    name =  this.pokemon.name,
    urlImg =  this.pokemon.urlImg,
    species =  this.about.species,
    height =  this.about.height,
    weight =  this.about.weight,
    abilities =  this.about.abilities,
    stats = this.about.stats.map { it.toString() },
    types = this.pokemon.types,
    isFav = this.pokemon.isFav
)

fun PokemonDetailEntity.toModel() = PokemonDetailModel(
    pokemon = PokemonModel(
        id = this.id,
        name =  this.name,
        urlImg =  this.urlImg,
        types = this.types,
        isFav = this.isFav
    ),
    about = About(
        species =  this.species,
        height =  this.height,
        weight =  this.weight,
        abilities =  this.abilities,
        stats = this.stats.map { it.toLong() }
    )
)

fun Type.toTypeString() = this.type.name

fun PokePageDto.toPokePage() : PokePage = PokePage(
    nextPage = this.next,
    previousPage = this.previous,
    pokemons = this.results
)
