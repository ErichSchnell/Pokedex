package com.jereschneider.pokedex.data.repository

import com.jereschneider.pokedex.data.interfaces.PokemonListDataSourceInterface
import com.jereschneider.pokedex.domain.interfaces.PokemonRawListRepositoryInterface
import com.jereschneider.pokedex.domain.models.PokemonRaw
import com.jereschneider.pokedex.domain.models.StatusResult

class PokemonRawListRepository(
    private val datasource: PokemonListDataSourceInterface
) : PokemonRawListRepositoryInterface {
    private var previousPage: String? = null
    private var nextPage: String? = null

    override suspend fun fetchPokePage(): StatusResult<List<PokemonRaw>> = fetchPage(null)

    override suspend fun nextPokePage(): StatusResult<List<PokemonRaw>> = fetchPage(nextPage)

    override suspend fun previousPokePage(): StatusResult<List<PokemonRaw>> = fetchPage(previousPage)

    private suspend fun fetchPage(navigateTo: String?): StatusResult<List<PokemonRaw>> =
        when (val result = datasource.fetchPokePage(navigateTo)) {
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> {
                previousPage = result.value.previousPage
                nextPage = result.value.nextPage
                StatusResult.Success(result.value.pokemons)
            }
        }
}