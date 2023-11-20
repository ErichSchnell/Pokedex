package com.jereschneider.pokedex.domain.usecases

import com.jereschneider.pokedex.domain.interfaces.PokemonDetailRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.models.StatusResult

class GetPokemonDetailUseCase(
    private val pokemonDetailRepository: PokemonDetailRepositoryInterface
) {
    suspend operator fun invoke(pokemonName: String): StatusResult<PokemonDetailModel> =
        when (val res = pokemonDetailRepository.fetchPokemonDetail(pokemonName)) {
            is StatusResult.Error -> StatusResult.Error(res.message)
            is StatusResult.Success -> StatusResult.Success(res.value)
        }
}