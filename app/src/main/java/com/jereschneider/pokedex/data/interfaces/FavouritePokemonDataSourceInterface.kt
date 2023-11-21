package com.jereschneider.pokedex.data.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import kotlinx.coroutines.flow.Flow

interface FavouritePokemonDataSourceInterface {
    suspend fun savePokemon(pokemonDetailModel: PokemonDetailModel)
    suspend fun deletePokemon(pokemonDetailModel: PokemonDetailModel)
    suspend fun getFavourites() : Flow<List<PokemonDetailModel>>
}