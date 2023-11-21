package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.Favourite_StorageRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import javax.inject.Inject

class SetFavouritePokemonUseCase @Inject constructor(
    private val storageRepository: Favourite_StorageRepositoryInterface
) {
    suspend operator fun invoke(pokemonDetailModel: PokemonDetailModel){
        storageRepository.setFavourite(pokemonDetailModel)
    }
}