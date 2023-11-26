package com.jereschneider.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val VERSION_ACCESS_DB = 1

@Database(
    entities = [PokemonDetailEntity::class],
    version = VERSION_ACCESS_DB,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FavouritePokemonDB : RoomDatabase() {
    companion object {
        const val DB_NAME = "favouritePokemon_database"
    }

    abstract val favouritePokemonDAO: FavouritePokemonDAO
}