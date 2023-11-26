package com.jereschneider.pokedex.domain.interfaces

import com.jereschneider.pokedex.domain.models.PokemonDetailModel

interface Favourite_StorageRepositoryInterface {
    suspend fun setFavourite(pokemonDetailModel: PokemonDetailModel)
}
