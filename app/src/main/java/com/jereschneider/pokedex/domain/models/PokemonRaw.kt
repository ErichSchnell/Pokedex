package com.jereschneider.pokedex.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonRaw(
    val name: String,
    @SerialName("url")
    val urlPokemonDetail: String
)