package com.jereschneider.pokedex.data.datasources

import com.jereschneider.pokedex.data.interfaces.PokemonDetailRemoteDataSourceInterface
import com.jereschneider.pokedex.data.mappers.toPokemonDetailModel
import com.jereschneider.pokedex.data.network.BaseClient
import com.jereschneider.pokedex.data.network.fetchPokemonDetail
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.StatusResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDetailRemoteDataSource @Inject constructor(
    private val baseClient: BaseClient
) : PokemonDetailRemoteDataSourceInterface {
    override suspend fun getPokemonDetail(pokemonName: String): StatusResult<PokemonDetailModel> =
        withContext(Dispatchers.IO) {
            when (val res = baseClient.fetchPokemonDetail(pokemonName)) {
                is StatusResult.Error -> StatusResult.Error(res.message)
                is StatusResult.Success -> StatusResult.Success(res.value.toPokemonDetailModel())
            }
        }
}