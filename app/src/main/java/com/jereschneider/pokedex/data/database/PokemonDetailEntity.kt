package com.jereschneider.pokedex.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDetailEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val urlImg: String,
//    val types: List<String>,
    val species: String,
    val height: String,
    val weight: String,
    val abilities: String
)
