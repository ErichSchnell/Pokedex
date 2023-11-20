package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.domain.models.DetailState
import com.jereschneider.pokedex.ui.screens.common.ErrorView

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    pokemonName: String,
    onBackClick: () -> Unit,
    onSubscribe: () -> Unit
) {
    viewModel.getPokemonDetail(pokemonName)
    when(val state = viewModel.state.value){
        DetailState.Loading -> DetailContentLoading()
        is DetailState.Success -> DetailContent(
            detailPokemon = state.detailPokemon,
            onBackClick = { onBackClick() },
            onSubscribe = { onSubscribe() }
        )
        is DetailState.Error -> ErrorView(state.message)
    }
}