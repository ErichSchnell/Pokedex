package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.domain.models.HomeState
import com.jereschneider.pokedex.ui.screens.common.ErrorView

@Composable
fun HomeScreen(homeState: HomeState){
    when(homeState){
        HomeState.Loading -> HomeContentLoading()
        is HomeState.Success -> HomeContent(pokemons = homeState.pokemons)
        is HomeState.Error -> ErrorView(message = homeState.message)
    }
}