package com.jereschneider.pokedex.domain.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel

interface DeleteFavourite_StorageRepositoryInterface {
    suspend fun deleteFavourite(pokemonDetailModel: PokemonDetailModel)
}