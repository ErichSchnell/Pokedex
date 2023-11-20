package com.jereschneider.pokedex.data.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.domain.models.StatusResult

interface PokemonDetailRemoteDataSourceInterface {
    suspend fun getPokemonDetail(pokemonName: String): StatusResult<PokemonDetailModel>
}