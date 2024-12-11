package com.jereschneider.pokedex.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

private const val VERSION_ACCESS_DB = 2

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
class Migration1to2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE PokemonDetailEntity ADD COLUMN stats TEXT DEFAULT '' NOT NULL")
    }
}