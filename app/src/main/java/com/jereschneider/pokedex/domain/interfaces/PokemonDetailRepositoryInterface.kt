package com.jereschneider.pokedex.domain.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.domain.models.StatusResult

interface PokemonDetailRepositoryInterface {
    suspend fun fetchPokemonDetail(endpoint: String): StatusResult<PokemonDetailModel>
}