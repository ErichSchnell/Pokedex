package com.jereschneider.pokedex.domain.models

sealed class DetailState {
    data object Loading : DetailState()
    data class Success(val detailPokemon: PokemonDetailModel) : DetailState()
    data class Error(val message: String) : DetailState()
}