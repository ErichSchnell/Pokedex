package com.jereschneider.pokedex.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.usecases.SetFavouritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val setFavouritePokemonUseCase: SetFavouritePokemonUseCase
) : ViewModel() {

    fun setFavourite(pokemonDetailModel: PokemonDetailModel) = viewModelScope.launch{
        Log.e( "setFavourite: ", "SET FAV: $pokemonDetailModel")
        setFavouritePokemonUseCase(pokemonDetailModel)
    }
}