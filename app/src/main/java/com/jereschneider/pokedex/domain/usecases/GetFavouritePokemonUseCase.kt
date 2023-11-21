package com.jereschneider.pokedex.domain.usecases

import android.util.Log
import com.jereschneider.pokedex.domain.interfaces.GetFavourite_StorageRepositoryInterface
import javax.inject.Inject

class GetFavouritePokemonUseCase @Inject constructor(
    private val storageRepository: GetFavourite_StorageRepositoryInterface
) {
    suspend operator fun invoke(){
        storageRepository.getFavourites().collect{
            it.map { poke -> Log.e("invoke: ", "POKEMON $poke") }
        }
    }
}