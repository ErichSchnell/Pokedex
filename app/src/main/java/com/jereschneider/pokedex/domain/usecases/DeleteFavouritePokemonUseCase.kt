package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.DeleteFavourite_StorageRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import javax.inject.Inject

class DeleteFavouritePokemonUseCase @Inject constructor(
    private val storageRepository: DeleteFavourite_StorageRepositoryInterface
) {
    suspend operator fun invoke(pokemonDetailModel: PokemonDetailModel) {
        storageRepository.deleteFavourite(pokemonDetailModel)
    }
}