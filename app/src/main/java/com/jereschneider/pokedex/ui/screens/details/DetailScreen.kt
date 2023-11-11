package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.domain.models.DetailState
import com.jereschneider.pokedex.ui.screens.common.ErrorView

@Composable
fun DetailScreen(
    state: DetailState,
    onBackClick: () -> Unit,
    onSubscribe: () -> Unit
) {
    when(state){
        DetailState.Loading -> DetailContentLoading()
        is DetailState.Success -> DetailContent(
            detailPokemon = state.detailPokemon,
            onBackClick = { onBackClick() },
            onSubscribe = { onSubscribe() }
        )
        is DetailState.Error -> ErrorView(state.message)
    }
}