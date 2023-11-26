package com.jereschneider.pokedex.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritePokemonDAO {
    @Query("SELECT * FROM PokemonDetailEntity")
    fun getPokemons(): Flow<List<PokemonDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemonDetailEntity: PokemonDetailEntity)

    @Delete
    fun delete(pokemonDetailEntity: PokemonDetailEntity)
}