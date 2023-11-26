package com.jereschneider.pokedex.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jereschneider.pokedex.domain.models.PokemonDetailModel
import com.jereschneider.pokedex.domain.usecases.DeleteFavouritePokemonUseCase
import com.jereschneider.pokedex.domain.usecases.SetFavouritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val setFavouritePokemonUseCase: SetFavouritePokemonUseCase,
    private val deleteFavouritePokemonUseCase: DeleteFavouritePokemonUseCase,
) : ViewModel() {

    fun setFavourite(pokemonDetailModel: PokemonDetailModel) = viewModelScope.launch{
        setFavouritePokemonUseCase(pokemonDetailModel)
    }

    fun deleteFavourite(pokemonDetailModel: PokemonDetailModel) = viewModelScope.launch{
        deleteFavouritePokemonUseCase(pokemonDetailModel)
    }
}