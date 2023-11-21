package com.jereschneider.pokedex.data.datasources

import com.jereschneider.pokedex.data.database.FavouritePokemonDAO
import com.jereschneider.pokedex.data.interfaces.FavouritePokemonDataSourceInterface
import com.jereschneider.pokedex.data.mappers.toEntity
import com.jereschneider.pokedex.data.mappers.toModel
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouritePokemonDataSource @Inject constructor(
    private val db: FavouritePokemonDAO
) : FavouritePokemonDataSourceInterface {
    override suspend fun savePokemon(pokemonDetailModel: PokemonDetailModel) =
        withContext(Dispatchers.IO) {
            db.save(pokemonDetailModel.toEntity())
        }

    override suspend fun deletePokemon(pokemonDetailModel: PokemonDetailModel) =
        withContext(Dispatchers.IO) {
            db.delete(pokemonDetailModel.toEntity())
        }

    override suspend fun getFavourites(): Flow<List<PokemonDetailModel>> =
        withContext(Dispatchers.IO) {
            db.getPokemons().transform { list ->
                list.map {
                    it.toModel()
                }
            }
        }
}