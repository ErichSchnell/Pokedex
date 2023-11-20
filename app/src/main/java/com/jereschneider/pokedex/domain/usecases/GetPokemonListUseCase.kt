package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.PokemonRawListRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.StatusResult
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRawListRepository: PokemonRawListRepositoryInterface,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) {
    suspend operator fun invoke(): StatusResult<List<PokemonDetailModel>> =
        when ( val result = pokemonRawListRepository.nextPokePage() ) {
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> {
                val pokemons = result.value.mapNotNull {
                    when (val res = getPokemonDetailUseCase(it.name)) {
                        is StatusResult.Error -> null
                        is StatusResult.Success -> res.value
                    }
                }
                StatusResult.Success(pokemons)
            }
        }
}