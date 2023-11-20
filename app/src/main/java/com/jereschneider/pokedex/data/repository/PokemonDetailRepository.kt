package com.jereschneider.pokedex.data.repository

import com.jereschneider.pokedex.data.interfaces.PokemonDetailRemoteDataSourceInterface
import com.jereschneider.pokedex.domain.interfaces.PokemonDetailRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.domain.models.StatusResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDetailRepository @Inject constructor(
    private val remoteDatasource: PokemonDetailRemoteDataSourceInterface
) : PokemonDetailRepositoryInterface {
    private val cacheDetails = mutableListOf<CacheDetails>()

    override suspend fun fetchPokemonDetail(endpoint: String): StatusResult<PokemonModel> {
        val pokemonModel = cacheDetails.filter { it.endpoint == endpoint }

        if (pokemonModel.isNotEmpty()) return StatusResult.Success(pokemonModel.first().pokemonModel)

        return when(val result = remoteDatasource.getPokemonDetail(endpoint)){
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> {
                cacheDetails.add(CacheDetails(endpoint, result.value))
                StatusResult.Success(result.value)
            }
        }
    }
}

data class CacheDetails(
    val endpoint: String,
    val pokemonModel: PokemonModel
)