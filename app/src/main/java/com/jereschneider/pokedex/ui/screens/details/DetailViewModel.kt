package com.jereschneider.pokedex.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jereschneider.pokedex.domain.models.DetailState
import com.jereschneider.pokedex.ui.models.HomeState
import com.jereschneider.pokedex.domain.models.StatusResult
import com.jereschneider.pokedex.domain.usecases.GetPokemonDetailUseCase
import com.jereschneider.pokedex.domain.usecases.GetPokemonListUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    var state: MutableState<DetailState> = mutableStateOf(DetailState.Loading)
        private set

    fun getPokemonDetail(pokemonName: String) = viewModelScope.launch {
        when(val res = getPokemonDetailUseCase(pokemonName)){
            is StatusResult.Error -> state.value = DetailState.Error(res.message)
            is StatusResult.Success ->  state.value = DetailState.Success(res.value)
        }
    }
}

