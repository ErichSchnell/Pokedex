package com.jereschneider.pokedex.data.models

import com.jereschneider.pokedex.domain.models.PokemonRaw
import kotlinx.serialization.*

@Serializable
data class PokePageDto (
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<PokemonRaw>
)