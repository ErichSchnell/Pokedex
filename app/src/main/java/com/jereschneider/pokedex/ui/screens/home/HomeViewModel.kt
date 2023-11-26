package com.jereschneider.pokedex.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jereschneider.pokedex.ui.models.HomeState
import com.jereschneider.pokedex.domain.models.StatusResult
import com.jereschneider.pokedex.domain.usecases.GetFavouritePokemonUseCase
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import com.jereschneider.pokedex.ui.models.ComponentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getFavouritePokemonUseCase: GetFavouritePokemonUseCase,
) : ViewModel() {

    init {
        fetchPage()
        getFavouritePokemons()
    }

    var state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
        private set

    private fun fetchPage() = viewModelScope.launch {
        when (val res = getPokemonListUseCase()) {
            is StatusResult.Error -> state.update {
                it.copy(listPokemons = ComponentState.Error(res.message))
            }

            is StatusResult.Success -> state.update {
                it.copy(listPokemons = ComponentState.Success(res.value))
            }
        }
    }

    private fun getFavouritePokemons() = viewModelScope.launch {
        getFavouritePokemonUseCase().collect { listFavourite ->
            state.update {
                it.copy(favPokemons = ComponentState.Success(listFavourite))
            }
        }
    }
}