package com.jereschneider.pokedex.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jereschneider.pokedex.ui.models.HomeState
import com.jereschneider.pokedex.domain.models.StatusResult
import com.jereschneider.pokedex.domain.usecases.GetFavouritePokemonUseCase
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getFavouritePokemonUseCase: GetFavouritePokemonUseCase
) : ViewModel() {

    init {
        fetchPage()
        getFavouritePokemons()
    }

    var state: MutableState<HomeState> = mutableStateOf(HomeState.Loading)
        private set

    private fun fetchPage() = viewModelScope.launch {
        when(val res = getPokemonListUseCase()){
            is StatusResult.Error -> state.value = HomeState.Error(res.message)
            is StatusResult.Success ->  state.value = HomeState.Success(res.value)
        }
    }

    fun getFavouritePokemons() = viewModelScope.launch{
        getFavouritePokemonUseCase()
    }

}