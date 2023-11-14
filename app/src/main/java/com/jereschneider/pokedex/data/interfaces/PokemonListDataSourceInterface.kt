package com.jereschneider.pokedex.data.interfaces

import com.jereschneider.pokedex.domain.models.PokePage
import com.jereschneider.pokedex.domain.models.StatusResult

interface PokemonListDataSourceInterface {
    suspend fun fetchPokePage(navigateToPage: String?) : StatusResult<PokePage>
}