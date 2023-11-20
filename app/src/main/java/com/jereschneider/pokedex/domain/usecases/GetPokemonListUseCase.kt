package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.PokemonRawListRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonModel
import com.jereschneider.pokedex.domain.models.StatusResult

class GetPokemonListUseCase(
    private val pokemonRawListRepository: PokemonRawListRepositoryInterface,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) {
    suspend operator fun invoke(): StatusResult<List<PokemonModel>> =
        when ( val result = pokemonRawListRepository.nextPokePage() ) {
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> {
                val pokemons = result.value.mapNotNull {
                    when (val res = getPokemonDetailUseCase(it.name)) {
                        is StatusResult.Error -> null
                        is StatusResult.Success -> res.value.pokemon
                    }
                }
                StatusResult.Success(pokemons)
            }
        }
}