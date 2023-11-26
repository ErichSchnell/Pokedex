package com.jereschneider.pokedex.domain.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import kotlinx.coroutines.flow.Flow

interface GetFavourite_StorageRepositoryInterface {
    suspend fun getFavourites() : Flow<List<PokemonDetailModel>>
}