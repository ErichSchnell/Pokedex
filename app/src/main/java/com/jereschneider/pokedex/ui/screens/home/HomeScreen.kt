package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.ui.models.HomeState
import com.jereschneider.pokedex.ui.screens.common.ErrorView

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    goToDetail: (String) -> Unit
) {
    when (val homeState = homeViewModel.state.value) {
        HomeState.Loading -> HomeContentLoading()
        is HomeState.Success -> HomeContent(pokemons = homeState.pokemons) { goToDetail(it) }
        is HomeState.Error -> ErrorView(message = homeState.message)
    }
}