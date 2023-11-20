package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.PokemonDetailRepositoryInterface
import com.jereschneider.pokedex.domain.interfaces.PokemonRawListRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.domain.models.StatusResult
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRawListRepository: PokemonRawListRepositoryInterface,
    private val pokemonDetailRepository: PokemonDetailRepositoryInterface
) {
    suspend operator fun invoke(): StatusResult<List<PokemonModel>> =
        when ( val result = pokemonRawListRepository.nextPokePage() ) {
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> {
                val pokemons = result.value.mapNotNull {
                    when (val res = pokemonDetailRepository.fetchPokemonDetail(it.urlPokemonDetail)) {
                        is StatusResult.Error -> null
                        is StatusResult.Success -> res.value
                    }
                }
                StatusResult.Success(pokemons)
            }
        }
}