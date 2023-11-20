package com.jereschneider.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailModel(
    val pokemon: PokemonModel,
    val about: About,
) : Parcelable

@Parcelize
data class About(
    val species: String,
    val height: String,
    val weight: String,
    val abilities: String
) : Parcelable
