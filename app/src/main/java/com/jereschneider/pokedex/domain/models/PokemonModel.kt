package com.jereschneider.pokedex.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val id: Long,
    val name: String,
    val urlImg: String,
    val types: List<String>
) : Parcelable
