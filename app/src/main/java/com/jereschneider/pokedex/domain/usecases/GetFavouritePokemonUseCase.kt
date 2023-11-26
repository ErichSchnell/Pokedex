package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.GetFavourite_StorageRepositoryInterface
import javax.inject.Inject

class GetFavouritePokemonUseCase @Inject constructor(
    private val storageRepository: GetFavourite_StorageRepositoryInterface
) {
    suspend operator fun invoke() = storageRepository.getFavourites()
}