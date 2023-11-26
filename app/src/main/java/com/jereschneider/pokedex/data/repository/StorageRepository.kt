package com.jereschneider.pokedex.data.repository

import com.jereschneider.pokedex.data.interfaces.FavouritePokemonDataSourceInterface
import com.jereschneider.pokedex.domain.interfaces.DeleteFavourite_StorageRepositoryInterface
import com.jereschneider.pokedex.domain.interfaces.Favourite_StorageRepositoryInterface
import com.jereschneider.pokedex.domain.interfaces.GetFavourite_StorageRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    private val dataSource: FavouritePokemonDataSourceInterface
) : Favourite_StorageRepositoryInterface,
    DeleteFavourite_StorageRepositoryInterface,
    GetFavourite_StorageRepositoryInterface{
    override suspend fun deleteFavourite(pokemonDetailModel: PokemonDetailModel) {
        dataSource.deletePokemon(pokemonDetailModel)
    }

    override suspend fun setFavourite(pokemonDetailModel: PokemonDetailModel) {
        dataSource.savePokemon(pokemonDetailModel)
    }

    override suspend fun getFavourites(): Flow<List<PokemonDetailModel>> {
        return dataSource.getFavourites()
    }

}