package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.runtime.Composable
import com.jereschneider.pokedex.domain.models.HomeState

@Composable
fun HomeScreen(homeState: HomeState){
    when(homeState){
        HomeState.Loading -> HomeContentLoading()
        is HomeState.Success -> HomeContent(pokemons = homeState.pokemons)
        is HomeState.Error -> HomeContentError(message = homeState.message)
    }
}