package com.jereschneider.pokedex.domain.interfaces

import com.jereschneider.pokedex.domain.models.PokemonRaw
import com.jereschneider.pokedex.domain.models.StatusResult

interface PokemonRawListRepositoryInterface {
    suspend fun fetchPokePage() : StatusResult<List<PokemonRaw>>
    suspend fun nextPokePage() : StatusResult<List<PokemonRaw>>
    suspend fun previousPokePage() : StatusResult<List<PokemonRaw>>
}