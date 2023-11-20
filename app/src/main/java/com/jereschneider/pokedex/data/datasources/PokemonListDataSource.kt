package com.jereschneider.pokedex.data.datasources

import com.jereschneider.pokedex.data.interfaces.PokemonListDataSourceInterface
import com.jereschneider.pokedex.data.mappers.toPokePage
import com.jereschneider.pokedex.data.network.BaseClient
import com.jereschneider.pokedex.data.network.fetchPokemonPage
import com.jereschneider.pokedex.domain.models.PokePage
import com.jereschneider.pokedex.domain.models.StatusResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonListDataSource @Inject constructor(
    private val baseClient: BaseClient
) : PokemonListDataSourceInterface {
    override suspend fun fetchPokePage(navigateToPage: String?): StatusResult<PokePage> =
        withContext(Dispatchers.IO) {
            when (val result = baseClient.fetchPokemonPage(navigateToPage)) {
                is StatusResult.Error -> StatusResult.Error(result.message)
                is StatusResult.Success -> StatusResult.Success(result.value.toPokePage())
            }
        }
}